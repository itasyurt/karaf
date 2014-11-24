package org.itasyurt.jsonize.adapter;

public class FloatAdapter extends AbstractTypeAdapter<Float> {

	@Override
	public Float convertToObject(String s) {
		return new Float(s);
	}

}
