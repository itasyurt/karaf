package org.itasyurt.karaf.dao.common.characteristic.specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.itasyurt.karaf.dao.common.BaseJpaDao;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification_;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption_;
import org.springframework.stereotype.Repository;

@Repository
public class SelectionCharacteristicSpecificationJpaDao extends BaseJpaDao<SelectionCharacteristicSpecification> implements SelectionCharacteristicSpecificationDao {

	@Override
	protected Root<SelectionCharacteristicSpecification> constructRoot(CriteriaQuery<SelectionCharacteristicSpecification> c) {

		Root<SelectionCharacteristicSpecification> root = c.from(clazz);
		root.fetch(SelectionCharacteristicSpecification_.name, JoinType.LEFT);
		root.fetch(SelectionCharacteristicSpecification_.options, JoinType.LEFT).fetch(SpecOption_.name, JoinType.LEFT);
		return root;
	}

}
