package org.itasyurt.karaf.dao.characteristic.specification.conditional;

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
import org.itasyurt.karaf.domain.characteristic.specification.conditional.ConditionalCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.ConditionalCharacteristicSpecification_;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.SpecOptionConditionalCharacteristic;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.SpecOptionConditionalCharacteristic_;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.SpecOptionConditionalRelation;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.SpecOptionConditionalRelation_;
import org.springframework.stereotype.Repository;

@Repository
public class ConditionalCharacteristicSpecificationJpaDao extends BaseJpaDao<ConditionalCharacteristicSpecification> implements ConditionalCharacteristicSpecificationDao {

	@Override
	protected Root<ConditionalCharacteristicSpecification> constructDeepRoot(CriteriaQuery<ConditionalCharacteristicSpecification> c) {
		Root<ConditionalCharacteristicSpecification> root = c.from(clazz);
		root.fetch(CombinedCharacteristicSpecification_.name, JoinType.LEFT);
		FetchUtils.fetchCharacteristic(root.fetch(ConditionalCharacteristicSpecification_.primary));

		Fetch<ConditionalCharacteristicSpecification, SpecOptionConditionalRelation> conditionalsFetch = root.fetch(ConditionalCharacteristicSpecification_.conditionalCharacteristics, JoinType.LEFT);

		FetchUtils.specOptionFetch(conditionalsFetch.fetch(SpecOptionConditionalRelation_.primary));
		Fetch<SpecOptionConditionalRelation, SpecOptionConditionalCharacteristic> characteristicsFetch = conditionalsFetch.fetch(SpecOptionConditionalRelation_.conditionalCharacteristics);
		FetchUtils.fetchCharacteristic(characteristicsFetch.fetch(SpecOptionConditionalCharacteristic_.characteristic));

		return root;

	}

}
