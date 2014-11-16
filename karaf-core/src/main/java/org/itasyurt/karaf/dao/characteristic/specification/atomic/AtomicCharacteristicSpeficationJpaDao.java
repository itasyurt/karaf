package org.itasyurt.karaf.dao.characteristic.specification.atomic;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.itasyurt.karaf.dao.common.BaseJpaDao;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification_;
import org.springframework.stereotype.Repository;

@Repository
public class AtomicCharacteristicSpeficationJpaDao extends BaseJpaDao<AtomicCharacteristicSpecification> implements AtomicCharacteristicSpecificationDao {

	@Override
	protected Root<AtomicCharacteristicSpecification> constructDeepRoot(CriteriaQuery<AtomicCharacteristicSpecification> c) {
		Root<AtomicCharacteristicSpecification> root = c.from(clazz);
		root.fetch(AtomicCharacteristicSpecification_.name, JoinType.LEFT);
		return root;

	}

}
