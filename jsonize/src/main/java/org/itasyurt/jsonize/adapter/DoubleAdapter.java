package org.itasyurt.jsonize.adapter;

public class DoubleAdapter extends AbstractTypeAdapter<Double> {

	@Override
	public Double convertToObject(String s) {
		return new Double(s);
	}

}
