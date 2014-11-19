package org.itasyurt.jsonize.anottationprocessor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.itasyurt.jsonize.annotations.JsonDetail;
import org.itasyurt.jsonize.annotations.JsonSummary;

public class JsonAnnotationProcessor {

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
				Object collectionResult = FieldUtils.readField(obj, child.getField().getName(), true);
				if (collectionResult != null) {
					List collectionJson = new ArrayList();
					for (Object item : (Collection) collectionResult) {
						collectionJson.add(convertToJson(child, item));
					}
					result.put(child.getField().getName(), collectionJson);

				}
			} else {
				value = FieldUtils.readField(child.getField(), obj, true);

				if (value != null) {
					result.put(child.getField().getName(), convertToJson(child, value));
				}
			}

		} catch (Exception exc) {

			throw new RuntimeException(exc);

		}
	}
}
