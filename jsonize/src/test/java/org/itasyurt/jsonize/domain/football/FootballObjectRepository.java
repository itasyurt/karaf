package org.itasyurt.jsonize.domain.football;

import java.util.HashMap;
import java.util.Map;

import org.itasyurt.jsonize.repository.ObjectRepository;

public class FootballObjectRepository implements ObjectRepository {

	Map<Class, Map<Object, Object>> objectRepository = new HashMap<Class, Map<Object, Object>>();

	@SuppressWarnings("unchecked")
	public <T> T find(Class<T> clazz, Object key) {

		Map<Object, Object> classRepo = objectRepository.get(clazz);
		if (classRepo != null) {

			return (T) classRepo.get(key);
		} else {
			return null;
		}

	}

	public void putObject(Object key, Object object) {

		Map<Object, Object> classRepo = objectRepository.get(object.getClass());
		if (classRepo == null) {
			classRepo = new HashMap<Object, Object>();
			objectRepository.put(object.getClass(), classRepo);
		}
		classRepo.put(key, object);
	}

	public void put(BaseEntity entity) {
		putObject(entity.getId(), entity);
	}

}
