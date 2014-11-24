package org.itasyurt.jsonize.adapter;

public class BooleanAdapter extends AbstractTypeAdapter<Boolean> {

	@Override
	public Boolean convertToObject(String s) {
	
		return new Boolean(s);
	}

}
