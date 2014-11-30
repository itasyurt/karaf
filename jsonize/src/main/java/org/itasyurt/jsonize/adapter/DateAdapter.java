package org.itasyurt.jsonize.adapter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateAdapter extends AbstractTypeAdapter<Date> {

	@Override
	public Date convertToObject(String s) {
		Long l = new Long(s);
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(l);

		return cal.getTime();
	}

	@Override
	public String convertToString(Object obj) {

		return "" + ((Date)obj).getTime();
	}

}
