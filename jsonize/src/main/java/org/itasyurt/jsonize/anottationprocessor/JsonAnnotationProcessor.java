package org.itasyurt.jsonize.anottationprocessor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.itasyurt.jsonize.annotations.JsonDetail;
import org.itasyurt.jsonize.annotations.JsonSubtype;
import org.itasyurt.jsonize.annotations.JsonSummary;

public class JsonAnnotationProcessor {

	private static final String CLASS_NAME = "@class";
	private static Set<Class<?>> wrapperTypes = new HashSet<Class<?>>();

	static {
		wrapperTypes.add(Boolean.class);
		wrapperTypes.add(Character.class);
		wrapperTypes.add(Byte.class);
		wrapperTypes.add(Short.class);
		wrapperTypes.add(Integer.class);
		wrapperTypes.add(Long.class);
		wrapperTypes.add(Float.class);
		wrapperTypes.add(Double.class);
		wrapperTypes.add(Void.class);
		wrapperTypes.add(String.class);
		wrapperTypes.add(Enum.class);
		wrapperTypes.add(Date.class);
	}

	public void printAnnotations(Object obj) {

		Class<? extends Object> objectClass = obj.getClass();

		System.out.println("Summary:");
		System.out.println(getAnnotatedFields(objectClass, JsonSummary.class));
		System.out.println("Detail:");
		System.out.println(getAnnotatedFields(objectClass, JsonDetail.class));

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Field> getAnnotatedFields(Class objectClass, Class annotation) {

		List<Field> results = new ArrayList<Field>();

		Field[] fields = objectClass.getDeclaredFields();
		if (fields != null) {
			for (Field field : fields) {
				if (field.getAnnotation(annotation) != null) {
					results.add(field);
				}
			}

		}
		if (objectClass.getSuperclass() != null) {
			results.addAll(getAnnotatedFields(objectClass.getSuperclass(), annotation));
		}
		return results;
	}

	public JsonTree getDetailedJsonTree(Class<?> objectClass) {

		return getDetailJsonTree(objectClass, true);

	}

	private JsonTree getDetailJsonTree(Class<?> objectClass, boolean detailed) {

		return getDetailJsonTree(objectClass, null, detailed);

	}

	private JsonTree getDetailJsonTree(Class<?> objectClass, Field field, boolean detailed) {

		JsonTree result = new JsonTree(field);

		result.getChildren().addAll(getJsonTree(objectClass, JsonSummary.class, false));

		if (detailed) {

			result.getChildren().addAll(getJsonTree(objectClass, JsonDetail.class, true));
		}
		return result;

	}

	private List<JsonTree> getDetailJsonChildren(Class<?> objectClass, Field field, boolean detailed) {

		List<JsonTree> result = new ArrayList<JsonTree>();

		result.addAll(getJsonTree(objectClass, JsonSummary.class, false));

		if (detailed) {

			result.addAll(getJsonTree(objectClass, JsonDetail.class, true));
		}
		return result;

	}

	private List<JsonTree> getJsonTree(Class<?> objectClass, Class<?> annotation, boolean detailed) {
		List<JsonTree> result = new ArrayList<JsonTree>();
		List<Field> detailedFields = getAnnotatedFields(objectClass, annotation);
		for (Field f : detailedFields) {

			JsonTree child = new JsonTree(f);
			Class fieldType;
			if (Collection.class.isAssignableFrom(f.getType())) {
				ParameterizedType genericType = (ParameterizedType) f.getGenericType();
				fieldType = (Class) genericType.getActualTypeArguments()[0];
				child.setCollection(true);

			} else if (Map.class.isAssignableFrom(f.getType())) {
				ParameterizedType genericType = (ParameterizedType) f.getGenericType();
				Class keyType = (Class) genericType.getActualTypeArguments()[1];
				fieldType = (Class) genericType.getActualTypeArguments()[1];
				child.setMap(true);
				if (!isPrimitive(keyType)) {
					child.setKeyComplex(true);
				}

			} else {
				fieldType = f.getType();
			}
			detailed = detailed && annotation.equals(JsonDetail.class);
			child.getChildren().addAll(getDetailJsonChildren(fieldType, f, detailed));
			result.add(child);

		}
		return result;
	}

	public Map<String, Object> convertToDetailedJson(Object obj) {

		JsonTree tree = getDetailedJsonTree(obj.getClass());

		Map<String, Object> result = new HashMap<String, Object>();
		List<JsonTree> children = tree.getChildren();
		for (JsonTree child : children) {
			convertChildToJson(obj, result, child);
		}

		return result;

	}

	private Object convertToJson(JsonTree tree, Object obj) {

		List<JsonTree> children = tree.getChildren();
		if (children.isEmpty()) {
			return obj;
		} else {
			Map<String, Object> result = new HashMap<String, Object>();
			if (isSubtype(obj.getClass())) {
				result.put(CLASS_NAME, obj.getClass().getName());
			}
			for (JsonTree child : children) {
				convertChildToJson(obj, result, child);
			}
			return result;

		}

	}

	private void convertChildToJson(Object obj, Map<String, Object> result, JsonTree child) {
		Object value;
		try {
			if (child.isCollection()) {
				convertListToJson(obj, result, child);
			} else if (child.isMap()) {

				convertMapToJson(obj, result, child);

			} else {
				convertObjectToJson(obj, result, child);
			}

		} catch (Exception exc) {

			throw new RuntimeException(exc);

		}
	}

	private void convertObjectToJson(Object obj, Map<String, Object> result, JsonTree child) throws IllegalAccessException {
		Object value;
		value = FieldUtils.readField(child.getField(), obj, true);

		if (value != null) {
			result.put(child.getField().getName(), convertToJson(child, value));
		}
	}

	private void convertListToJson(Object obj, Map<String, Object> result, JsonTree child) throws IllegalAccessException {
		Object collectionResult = FieldUtils.readField(obj, child.getField().getName(), true);
		if (collectionResult != null) {
			List collectionJson = new ArrayList();
			for (Object item : (Collection) collectionResult) {
				collectionJson.add(convertToJson(child, item));
			}
			result.put(child.getField().getName(), collectionJson);

		}
	}

	private void convertMapToJson(Object obj, Map<String, Object> result, JsonTree child) throws IllegalAccessException {
		Object mapResult = FieldUtils.readField(obj, child.getField().getName(), true);
		if (mapResult != null) {
			if (child.isKeyComplex()) {
				List collectionJson = new ArrayList();
				for (Object item : ((Map) mapResult).values()) {
					collectionJson.add(convertToJson(child, item));
				}
				result.put(child.getField().getName(), collectionJson);

			} else {
				Map<String, Object> mapJson = new HashMap<String, Object>();
				for (Object item : ((Map) mapResult).entrySet()) {
					Entry entry = (Entry) item;
					mapJson.put(entry.getKey().toString(), entry.getValue());

				}
				result.put(child.getField().getName(), mapJson);

			}

		}
	}

	public static boolean isPrimitive(Class clazz) {

		return wrapperTypes.contains(clazz);
	}

	public static boolean isSubtype(Class clazz) {

		return clazz.getAnnotation(JsonSubtype.class) != null;
	}

}
