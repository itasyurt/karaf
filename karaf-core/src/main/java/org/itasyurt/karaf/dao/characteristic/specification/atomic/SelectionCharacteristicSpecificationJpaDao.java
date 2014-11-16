package org.itasyurt.karaf.dao.characteristic.specification.atomic;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.itasyurt.karaf.dao.FetchUtils;
import org.itasyurt.karaf.dao.common.BaseJpaDao;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification_;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption_;
import org.springframework.stereotype.Repository;

@Repository
public class SelectionCharacteristicSpecificationJpaDao extends BaseJpaDao<SelectionCharacteristicSpecification> implements SelectionCharacteristicSpecificationDao {

	@Override
	protected Root<SelectionCharacteristicSpecification> constructDeepRoot(CriteriaQuery<SelectionCharacteristicSpecification> c) {

		Root<SelectionCharacteristicSpecification> root = c.from(clazz);
		root.fetch(SelectionCharacteristicSpecification_.name, JoinType.LEFT);
		Fetch<SelectionCharacteristicSpecification, SpecOption> specOptionFetch = root.fetch(SelectionCharacteristicSpecification_.options, JoinType.LEFT);
		FetchUtils.specOptionFetch(specOptionFetch);
		return root;
	}
}
