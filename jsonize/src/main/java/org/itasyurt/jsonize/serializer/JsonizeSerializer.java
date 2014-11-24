package org.itasyurt.jsonize.serializer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.itasyurt.jsonize.annotations.JsonSubtype;
import org.itasyurt.jsonize.anottationprocessor.JsonAnnotationProcessor;
import org.itasyurt.jsonize.anottationprocessor.JsonTree;

public class JsonizeSerializer {
	
	private static final String CLASS_NAME = "@class";

	

	
	public Map<String, Object> convertToDetailedJson(Object obj) {

		JsonTree tree = getDetailedJsonTree(obj.getClass());

		Map<String, Object> result = new HashMap<String, Object>();
		List<JsonTree> children = tree.getChildren();
		for (JsonTree child : children) {
			convertChildToJson(obj, result, child);
		}

		return result;

	}

	private JsonTree getDetailedJsonTree(Class<? extends Object> class1) {
		
		return new  JsonAnnotationProcessor().getDetailedJsonTree(class1);
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
	
	public static boolean isSubtype(Class clazz) {

		return clazz.getAnnotation(JsonSubtype.class) != null;
	}
	

	


}
