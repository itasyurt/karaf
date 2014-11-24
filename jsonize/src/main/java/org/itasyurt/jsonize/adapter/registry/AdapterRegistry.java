package org.itasyurt.jsonize.adapter.registry;

import org.itasyurt.jsonize.adapter.AbstractTypeAdapter;

public interface AdapterRegistry {

	public <T> AbstractTypeAdapter<T> getTypeAdapter(Class<T> clazz);
}
