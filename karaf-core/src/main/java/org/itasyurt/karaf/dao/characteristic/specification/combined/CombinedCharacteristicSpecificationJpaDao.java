package org.itasyurt.karaf.dao.characteristic.specification.combined;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.itasyurt.karaf.dao.FetchUtils;
import org.itasyurt.karaf.dao.common.BaseJpaDao;
import org.itasyurt.karaf.domain.characteristic.specification.combined.CombinedCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.combined.CombinedCharacteristicSpecification_;
import org.itasyurt.karaf.domain.characteristic.specification.combined.SpecOptionCombinedRelation;
import org.itasyurt.karaf.domain.characteristic.specification.combined.SpecOptionCombinedRelation_;
import org.itasyurt.karaf.domain.characteristic.specification.combined.SpecOptionRelatedOptions;
import org.itasyurt.karaf.domain.characteristic.specification.combined.SpecOptionRelatedOptions_;
import org.springframework.stereotype.Repository;

@Repository
public class CombinedCharacteristicSpecificationJpaDao extends BaseJpaDao<CombinedCharacteristicSpecification> implements CombinedCharacteristicSpecificationDao {

	@Override
	protected Root<CombinedCharacteristicSpecification> constructDeepRoot(CriteriaQuery<CombinedCharacteristicSpecification> c) {
		Root<CombinedCharacteristicSpecification> root = c.from(clazz);
		root.fetch(CombinedCharacteristicSpecification_.name, JoinType.LEFT);
		FetchUtils.fetchCharacteristic(root.fetch(CombinedCharacteristicSpecification_.primary));
		FetchUtils.fetchCharacteristic(root.fetch(CombinedCharacteristicSpecification_.secondary));
		Fetch<CombinedCharacteristicSpecification, SpecOptionCombinedRelation> relatedOptionsFetch = root.fetch(CombinedCharacteristicSpecification_.relatedOptions, JoinType.LEFT);

		FetchUtils.specOptionFetch(relatedOptionsFetch.fetch(SpecOptionCombinedRelation_.primary));

		Fetch<SpecOptionCombinedRelation, SpecOptionRelatedOptions> relatedOptions = relatedOptionsFetch.fetch(SpecOptionCombinedRelation_.relatedOptions, JoinType.LEFT);
		FetchUtils.specOptionFetch(relatedOptions.fetch(SpecOptionRelatedOptions_.option, JoinType.LEFT));

		return root;

	}

}
