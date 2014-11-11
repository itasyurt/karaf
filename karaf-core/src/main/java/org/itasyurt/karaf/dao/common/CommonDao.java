package org.itasyurt.karaf.dao.common;

import java.util.List;

public interface CommonDao {

	<T> T save(T entity);

	<T> List<T> getAll(Class<T> clazz);

	<T> T find(Class<T> class1, String id);
}
