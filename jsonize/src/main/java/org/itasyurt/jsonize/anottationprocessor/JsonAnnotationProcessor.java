package org.itasyurt.jsonize.anottationprocessor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

}
