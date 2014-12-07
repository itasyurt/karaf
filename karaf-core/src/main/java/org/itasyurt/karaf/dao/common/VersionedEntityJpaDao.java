package org.itasyurt.karaf.dao.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.itasyurt.karaf.domain.entity.VersionedEntity;
import org.itasyurt.karaf.domain.entity.VersionedEntity_;
import org.springframework.stereotype.Repository;

@Repository
public abstract class VersionedEntityJpaDao<T extends VersionedEntity> extends BaseJpaDao<T> implements VersionedEntityDao<T> {

	@PersistenceContext(unitName = "emf")
	protected EntityManager entityManager;

	@Override
	public T findByCode(String code) {
		T result = null;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> c = cb.createQuery(clazz);
		Root<T> root = constructDeepRoot(c);
		c.select(root).distinct(true);
		c.where(cb.equal(root.get(VersionedEntity_.code), code));

		List<T> resultList = entityManager.createQuery(c).getResultList();

		if (resultList != null && !resultList.isEmpty()) {
			result = resultList.get(0);
		}
		return result;
	}

}
