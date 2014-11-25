package org.itasyurt.jsonize.repository;

public interface ObjectRepository {

	<T> T find(Class<T> clazz, Object key);
}
