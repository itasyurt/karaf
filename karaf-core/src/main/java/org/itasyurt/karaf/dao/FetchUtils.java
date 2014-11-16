package org.itasyurt.karaf.dao;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;

import org.itasyurt.karaf.domain.characteristic.specification.atomic.CharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.CharacteristicSpecification_;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption_;

public class FetchUtils {

	public static void specOptionFetch(Fetch<?, SpecOption> specOptionFetch) {
		specOptionFetch.fetch(SpecOption_.name, JoinType.LEFT);
	}

	public static void fetchCharacteristic(Fetch<?, ? extends CharacteristicSpecification> csFetch) {
		csFetch.fetch(CharacteristicSpecification_.name);

	}
}
