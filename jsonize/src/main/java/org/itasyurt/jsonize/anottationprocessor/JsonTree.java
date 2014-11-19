package org.itasyurt.jsonize.anottationprocessor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JsonTree {

	private Field field;
	private boolean isCollection;

	private List<JsonTree> children = new ArrayList<JsonTree>();

	public JsonTree(Field f) {
		this.field = f;
	}

	public void addChild(JsonTree child) {
		this.children.add(child);
	}

	public List<JsonTree> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return "JsonTree [field=" + field + "]";
	}

	public Field getField() {
		return field;
	}

	public boolean isCollection() {
		return isCollection;
	}

	public void setCollection(boolean isCollection) {
		this.isCollection = isCollection;
	}

}
