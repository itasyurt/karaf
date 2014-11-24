package org.itasyurt.jsonize.adapter;

public class IntegerAdapter extends AbstractTypeAdapter<Integer> {

	@Override
	public Integer convertToObject(String s) {
		return new Integer(s);
	}

}
