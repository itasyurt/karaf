package org.itasyurt.karaf.dao.common;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.itasyurt.karaf.domain.entity.BaseEntity;
import org.itasyurt.karaf.domain.entity.BaseEntity_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseJpaDao<T extends BaseEntity> implements BaseDao<T> {

	@PersistenceContext(unitName = "emf")
	protected EntityManager entityManager;

	@Autowired
	CommonDao commonDao;

	protected Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseJpaDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public T save(T entity) {
		// TODO Auto-generated method stub
		return commonDao.save(entity);
	}

	@Override
	public T update(T entity) {
		// TODO Auto-generated method stub
		return commonDao.update(entity);
	}

	@Override
	public void delete(String id) {
		commonDao.delete(clazz, id);

	}

	@Override
	public List<T> getAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> c = cb.createQuery(clazz);
		Root<T> root = constructRoot(c);
		c.select(root);
		return entityManager.createQuery(c).getResultList();
	}

	@Override
	public T find(String id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> c = cb.createQuery(clazz);
		Root<T> root = constructRoot(c);
		c.select(root);
		c.where(cb.equal(root.get(BaseEntity_.id), id));

		return entityManager.createQuery(c).getSingleResult();
	}

	protected abstract Root<T> constructRoot(CriteriaQuery<T> c);
}
