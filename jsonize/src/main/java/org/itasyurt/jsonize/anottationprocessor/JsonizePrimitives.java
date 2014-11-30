package org.itasyurt.jsonize.anottationprocessor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonizePrimitives {

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

	public static boolean isPrimitive(Class clazz) {

		Class current = clazz;
		while (current != null) {
			if (wrapperTypes.contains(current)) {
				return true;
			}
			current = current.getSuperclass();

		}
		return false;
	}

}
