package org.itasyurt.jsonize.serializer;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
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

public class JsonizeDeserializer {

	private static final String CLASS_NAME = "@class";

	private JsonAnnotationProcessor annotationProcessor = new JsonAnnotationProcessor();

	private AdapterRegistry adapterRegistry = new DefaultAdapterRegistry();

	public <T> T convertFromJson(Class<T> clazz, Map<String, Object> jsonMap) {

		JsonTree tree = annotationProcessor.getDetailedJsonTree(clazz);

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

	private void setFieldValue(JsonTree currentNode, Object currentJsonObject, Object resultObject) {

		if (currentJsonObject != null) {
			if (currentNode.isCollection()) {
				deserializeList(currentNode, currentJsonObject, resultObject);
			} else if (currentNode.isMap()) {
				if (currentNode.isKeyComplex()) {
					deserializeComplexKeyMap(currentNode, currentJsonObject, resultObject);
				} else {
					deserializePrimitiveKeyMap(currentNode, currentJsonObject, resultObject);

				}

			} else {
				Class<?> fieldType = currentNode.getField().getType();
				if (isPrimitive(fieldType)) {

					AbstractTypeAdapter<?> adapter = getAdapterRegistry().getTypeAdapter(fieldType);
					Object value = adapter.convertToObject((String) currentJsonObject);

					writeField(currentNode, resultObject, value);

				} else {

					Map objectMap = (Map) currentJsonObject;
					Object value = objectMap.containsKey(CLASS_NAME) ? createNewInstance((String) objectMap.get(CLASS_NAME)) : createNewInstance(fieldType);

					writeObject(currentNode, objectMap, value);
					writeField(currentNode, resultObject, value);
				}
			}
		}

	}

	@SuppressWarnings("rawtypes")
	private void deserializeList(JsonTree currentNode, Object currentJsonObject, Object target) {
		ParameterizedType genericType = (ParameterizedType) currentNode.getField().getGenericType();
		Class type = (Class) genericType.getActualTypeArguments()[0];
		List listObject = (List) currentJsonObject;
		List resultList = new ArrayList();
		for (Object objEntry : listObject) {
			if (isPrimitive(type)) {
				Object objectValue = getAdapterRegistry().getTypeAdapter(objEntry.getClass()).convertToObject((String) objEntry);
				resultList.add(objectValue);
			} else {

				Map objectMap = (Map) objEntry;
				Object objectValue = objectMap.containsKey(CLASS_NAME) ? createNewInstance((String) objectMap.get(CLASS_NAME)) : createNewInstance(type);
				
				writeObject(currentNode, objectMap, objectValue);
				resultList.add(objectValue);
			}

		}
		writeField(currentNode, target, resultList);

	}

	
	@SuppressWarnings("rawtypes")
	private void deserializeComplexKeyMap(JsonTree currentNode, Object currentJsonObject, Object target) {
		ParameterizedType genericType = (ParameterizedType) currentNode.getField().getGenericType();
		Class valueType = (Class) genericType.getActualTypeArguments()[1];
		List listObject = (List) currentJsonObject;
		Map resultMap = new HashMap();
		for (Object objEntry : listObject) {

			Map objectMap = (Map) objEntry;
			KeyContainer currentValue = (KeyContainer) (objectMap.containsKey(CLASS_NAME) ? createNewInstance((String) objectMap.get(CLASS_NAME)) : createNewInstance(valueType));
			
			writeObject(currentNode, objectMap, currentValue);
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
			Map objectMap = (Map) entry.getValue();
			Object objectValue = objectMap.containsKey(CLASS_NAME) ? createNewInstance((String) objectMap.get(CLASS_NAME)) : createNewInstance(entry.getValue().getClass());
			
			if (!isPrimitive(entry.getValue().getClass())) {
				writeObject(currentNode, objectMap, objectValue);
			}

			resultMap.put(objectKey, objectValue);
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
}
