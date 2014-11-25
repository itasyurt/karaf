package org.itasyurt.jsonize.anottationprocessor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JsonTree {

	private Field field;
	private boolean isCollection;
	private boolean isMap;
	private boolean isKeyComplex;
	private boolean isSummary= false;

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

	public boolean isMap() {
		return isMap;
	}

	public void setMap(boolean isMap) {
		this.isMap = isMap;
	}

	public boolean isKeyComplex() {
		return isKeyComplex;
	}

	public void setKeyComplex(boolean isKeyComplex) {
		this.isKeyComplex = isKeyComplex;
	}

	public boolean isSummary() {
		return isSummary;
	}

	public void setSummary(boolean isSummary) {
		this.isSummary = isSummary;
	}
	
	
	
	

}
