package org.itasyurt.karaf.dao.common;

import java.util.List;

public interface BaseDao<T> {

	T save(T entity);

	T update(T entity);

	T find(String id);

	void delete(String id);

	List<T> getAll();

}
