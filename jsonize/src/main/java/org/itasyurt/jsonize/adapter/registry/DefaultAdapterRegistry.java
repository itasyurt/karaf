package org.itasyurt.jsonize.adapter.registry;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.itasyurt.jsonize.adapter.AbstractTypeAdapter;
import org.itasyurt.jsonize.adapter.BigDecimalAdapter;
import org.itasyurt.jsonize.adapter.BooleanAdapter;
import org.itasyurt.jsonize.adapter.CharacterAdapter;
import org.itasyurt.jsonize.adapter.DateAdapter;
import org.itasyurt.jsonize.adapter.DoubleAdapter;
import org.itasyurt.jsonize.adapter.FloatAdapter;
import org.itasyurt.jsonize.adapter.IntegerAdapter;
import org.itasyurt.jsonize.adapter.LongAdapter;
import org.itasyurt.jsonize.adapter.ShortAdapter;
import org.itasyurt.jsonize.adapter.StringAdapter;

public class DefaultAdapterRegistry implements AdapterRegistry {

	@SuppressWarnings("rawtypes")
	Map<Class, AbstractTypeAdapter> adapterMap = new HashMap<Class, AbstractTypeAdapter>();

	public DefaultAdapterRegistry() {
		putAdapter(BigDecimal.class, new BigDecimalAdapter());
		putAdapter(Boolean.class, new BooleanAdapter());
		putAdapter(Character.class, new CharacterAdapter());
		putAdapter(Date.class, new DateAdapter());
		putAdapter(Double.class, new DoubleAdapter());
		putAdapter(Float.class, new FloatAdapter());
		putAdapter(Integer.class, new IntegerAdapter());
		putAdapter(Long.class, new LongAdapter());
		putAdapter(Short.class, new ShortAdapter());
		putAdapter(String.class, new StringAdapter());

	}

	@SuppressWarnings("unchecked")
	public <T> AbstractTypeAdapter<T> getTypeAdapter(Class<T> clazz) {

		AbstractTypeAdapter result = adapterMap.get(clazz);
		if (result == null) {

			if (Enum.class.isAssignableFrom(clazz)) {
				return (AbstractTypeAdapter<T>) new EnumAdapter((Class<Enum>) clazz);
			}

		}
		return result;
	}

	private <T> void putAdapter(Class<T> clazz, AbstractTypeAdapter<T> adapter) {
		adapterMap.put(clazz, adapter);
	}

}
