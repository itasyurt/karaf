package org.itasyurt.karaf.dao.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

@Component
public class CommonJpaDao implements CommonDao {

	@PersistenceContext(unitName = "emf")
	protected EntityManager entityManager;

	public <T> T save(T entity) {

		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	public <T> List<T> getAll(Class<T> clazz) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(clazz);
		Root<T> from = q.from(clazz);
		q.select(from);
		List<T> result = entityManager.createQuery(q).getResultList();
		if (result == null) {
			return new ArrayList<T>();
		} else {
			return result;
		}

	}

	public <T> T find(Class<T> clazz, String id) {

		return entityManager.find(clazz, id);

	}

	public <T> T update(T entity) {
		T result = entityManager.merge(entity);
		entityManager.flush();
		return result;

	}

	@Override
	public <T> void delete(Class<T> clazz, String id) {
		entityManager.remove(find(clazz, id));
		entityManager.flush();

	}
}
