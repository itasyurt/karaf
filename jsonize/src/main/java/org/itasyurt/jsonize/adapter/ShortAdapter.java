package org.itasyurt.jsonize.adapter;

public class ShortAdapter extends AbstractTypeAdapter<Short> {

	@Override
	public Short convertToObject(String s) {
		return new Short(s);
	}

}
