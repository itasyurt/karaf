package org.itasyurt.jsonize.adapter;

public abstract class AbstractTypeAdapter<T> {

	public abstract T convertToObject(String s);
	
	public String convertToString(T obj) {
		return obj.toString();
	}
}
