package org.itasyurt.jsonize.serializer;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.itasyurt.jsonize.adapter.AbstractTypeAdapter;
import org.itasyurt.jsonize.adapter.registry.AdapterRegistry;
import org.itasyurt.jsonize.adapter.registry.DefaultAdapterRegistry;
import org.itasyurt.jsonize.anottationprocessor.JsonAnnotationProcessor;
import org.itasyurt.jsonize.anottationprocessor.JsonTree;
import org.itasyurt.jsonize.anottationprocessor.JsonizePrimitives;
import org.itasyurt.jsonize.map.KeyContainer;
import org.itasyurt.jsonize.repository.ObjectRepository;

public class JsonizeDeserializer {

	private static final String CLASS_NAME = "@class";

	private JsonTreeFactory factory = new JsonTreeFactoryImpl();

	private AdapterRegistry adapterRegistry = new DefaultAdapterRegistry();

	private ObjectRepository repository;

	public <T> T convertFromJson(Class<T> clazz, Map<String, Object> jsonMap) {

		JsonTree tree = factory.getJsonTree(clazz);

		T result = createNewInstance(clazz);

		for (JsonTree child : tree.getChildren()) {
			setFieldValue(child, jsonMap.get(child.getField().getName()), result);

		}

		return result;
	}

	private <T> T createNewInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	}

	private Object createNewInstance(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	}

	private Class determineObjectType(Class type, Map objectMap) {
		if (objectMap.containsKey(CLASS_NAME)) {
			try {
				return Class.forName((String) objectMap.get(CLASS_NAME));
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return type;

	}

	private void setFieldValue(JsonTree currentNode, Object currentJsonObject, Object target) {

		if (currentJsonObject != null) {
			if (currentNode.isCollection()) {
				deserializeCollection(currentNode, currentJsonObject, target);
			} else if (currentNode.isMap()) {
				if (currentNode.isKeyComplex()) {
					deserializeComplexKeyMap(currentNode, currentJsonObject, target);
				} else {
					deserializePrimitiveKeyMap(currentNode, currentJsonObject, target);

				}

			} else {
				Class<?> fieldType = currentNode.getField().getType();
				if (isPrimitive(fieldType)) {

					AbstractTypeAdapter<?> adapter = getAdapterRegistry().getTypeAdapter(fieldType);
					Object value = adapter.convertToObject((String) currentJsonObject);

					writeField(currentNode, target, value);

				} else {

					Map objectMap = (Map) currentJsonObject;

					Object value;
					Class objectType = determineObjectType(fieldType, objectMap);
					value = createNewOrFind(currentNode, objectMap, objectType);

					writeField(currentNode, target, value);
				}
			}
		}

	}

	private Object createNewOrFind(JsonTree currentNode, Map objectMap, Class objectType) {
		Object value;
		if (currentNode.isSummary()) {
			value = getRepository().find(objectType, objectMap);
		} else {
			value = createNewInstance(objectType);

			writeObject(currentNode, objectMap, value);
		}
		return value;
	}

	@SuppressWarnings("rawtypes")
	private void deserializeCollection(JsonTree currentNode, Object currentJsonObject, Object target) {
		ParameterizedType genericType = (ParameterizedType) currentNode.getField().getGenericType();
		Class type = (Class) genericType.getActualTypeArguments()[0];
		List listObject = (List) currentJsonObject;
		Collection resultCollection = currentNode.isList() ? new ArrayList() : new HashSet();
		for (Object objEntry : listObject) {
			if (isPrimitive(type)) {
				Object objectValue = getAdapterRegistry().getTypeAdapter(objEntry.getClass()).convertToObject((String) objEntry);
				resultCollection.add(objectValue);
			} else {
				Map objectMap = (Map) objEntry;
				Class objectType = determineObjectType(type, objectMap);
				Object objectValue = createNewOrFind(currentNode, objectMap, objectType);
				resultCollection.add(objectValue);
			}

		}
		writeField(currentNode, target, resultCollection);

	}

	@SuppressWarnings("rawtypes")
	private void deserializeComplexKeyMap(JsonTree currentNode, Object currentJsonObject, Object target) {
		ParameterizedType genericType = (ParameterizedType) currentNode.getField().getGenericType();
		Class valueType = (Class) genericType.getActualTypeArguments()[1];
		List listObject = (List) currentJsonObject;
		Map resultMap = new HashMap();
		for (Object objEntry : listObject) {

			Map objectMap = (Map) objEntry;
			Class objectType = determineObjectType(valueType, objectMap);
			KeyContainer currentValue = (KeyContainer) createNewOrFind(currentNode, objectMap, objectType);

			Object objectKey = currentValue.getKey();
			resultMap.put(objectKey, currentValue);
		}
		writeField(currentNode, target, resultMap);

	}

	private void deserializePrimitiveKeyMap(JsonTree currentNode, Object currentJsonObject, Object target) {
		ParameterizedType genericType = (ParameterizedType) currentNode.getField().getGenericType();
		Class keyType = (Class) genericType.getActualTypeArguments()[0];
		Class valueType = (Class) genericType.getActualTypeArguments()[1];
		Map mapObject = (Map) currentJsonObject;
		Map resultMap = new HashMap();
		for (Object objEntry : mapObject.entrySet()) {
			Entry entry = (Entry) objEntry;
			Object objectKey = getAdapterRegistry().getTypeAdapter(entry.getKey().getClass()).convertToObject((String) entry.getKey());

			if (!isPrimitive(entry.getValue().getClass())) {
				Map objectMap = (Map) objEntry;
				Class objectType = determineObjectType(valueType, objectMap);
				Object objectValue = createNewOrFind(currentNode, objectMap, objectType);
				writeObject(currentNode, objectMap, objectValue);
				resultMap.put(objectKey, objectValue);
			} else {
				Object primitiveValue = getAdapterRegistry().getTypeAdapter(entry.getValue().getClass()).convertToObject((String) entry.getValue());
				resultMap.put(objectKey, primitiveValue);
			}

		}
		writeField(currentNode, target, resultMap);
	}

	private void writeObject(JsonTree currentNode, Map valueMap, Object target) {

		for (JsonTree child : currentNode.getChildren()) {
			setFieldValue(child, valueMap.get(child.getField().getName()), target);

		}

	}

	private void writeField(JsonTree currentNode, Object target, Object value) {
		try {

			FieldUtils.writeField(currentNode.getField(), target, value, true);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isPrimitive(Class clazz) {
		return JsonizePrimitives.isPrimitive(clazz);
	}

	public void setAdapterRegistry(AdapterRegistry adapterRegistry) {
		this.adapterRegistry = adapterRegistry;
	}

	protected AdapterRegistry getAdapterRegistry() {
		return adapterRegistry;
	}

	public void setRepository(ObjectRepository repository) {
		this.repository = repository;
	}

	protected ObjectRepository getRepository() {
		return repository;
	}

	public JsonTreeFactory getFactory() {
		return factory;
	}

	public void setFactory(JsonTreeFactory factory) {
		this.factory = factory;
	}
	
	

}
