package org.itasyurt.jsonize.adapter;

import java.math.BigDecimal;

public class BigDecimalAdapter extends AbstractTypeAdapter<BigDecimal> {

	@Override
	public BigDecimal convertToObject(String s) {
		
		return  new BigDecimal(s);
	}

}
