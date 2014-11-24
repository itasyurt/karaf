package org.itasyurt.jsonize.anottationprocessor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
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
		wrapperTypes.add(BigDecimal.class);
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
				if (!isPrimitive(keyType)) {
					child.setKeyComplex(true);
				}
				fieldType = (Class) genericType.getActualTypeArguments()[1];
				child.setMap(true);

			} else {
				fieldType = f.getType();
			}
			detailed = detailed && annotation.equals(JsonDetail.class);
			child.getChildren().addAll(getDetailJsonChildren(fieldType, f, detailed));
			result.add(child);

		}
		return result;
	}

	public static boolean isPrimitive(Class clazz) {

		return wrapperTypes.contains(clazz);
	}

}
