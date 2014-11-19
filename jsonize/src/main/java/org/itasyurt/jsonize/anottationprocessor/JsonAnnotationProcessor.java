package org.itasyurt.jsonize.anottationprocessor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

	public void printDetailJsonTree(Class<?> objectClass) {

		printDetailJsonTree(0, objectClass, true);

	}

	private void printDetailJsonTree(int depth, Class<?> objectClass, boolean detailed) {

		printFields(depth, objectClass, JsonSummary.class);

		if (detailed) {

			printFields(depth, objectClass, JsonDetail.class);
		}

	}

	private void printFields(int depth, Class<?> objectClass, Class<?> annotation) {
		List<Field> detailedFields = getAnnotatedFields(objectClass, annotation);
		for (Field f : detailedFields) {

			System.out.print(StringUtils.rightPad(" ", depth * 2));
			System.out.println(f);
			Class fieldType;
			if (List.class.isAssignableFrom(f.getType())) {
				ParameterizedType genericType = (ParameterizedType) f.getGenericType();
				fieldType = (Class) genericType.getActualTypeArguments()[0];

			} else {
				fieldType = f.getType();
			}
			boolean detailed = annotation.equals(JsonDetail.class);
			printDetailJsonTree(depth + 1, fieldType, detailed);

		}
	}
}
