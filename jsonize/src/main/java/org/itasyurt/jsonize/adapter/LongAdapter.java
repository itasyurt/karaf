package org.itasyurt.jsonize.adapter;

public class LongAdapter extends AbstractTypeAdapter<Long> {

	@Override
	public Long convertToObject(String s) {
		return new Long(s);
	}

}
