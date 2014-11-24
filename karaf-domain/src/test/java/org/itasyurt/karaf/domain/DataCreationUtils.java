package org.itasyurt.karaf.domain;

import java.util.List;
import java.util.Map;

import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.characteristic.specification.combined.CombinedCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.combined.SpecOptionCombinedRelation;
import org.itasyurt.karaf.domain.characteristic.specification.combined.SpecOptionRelatedOptions;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.ConditionalCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.SpecOptionConditionalCharacteristic;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.SpecOptionConditionalRelation;

public class DataCreationUtils {

	public static SelectionCharacteristicSpecification createSCS(String code, String... options) {
		SelectionCharacteristicSpecification result = new SelectionCharacteristicSpecification();

		result.setCode(code);
		result.setName(code);
		if (options != null) {
			for (String option : options) {

				SpecOption so = new SpecOption();
				so.setCode(option);
				so.setName(option);
				result.getOptions().add(so);
			}
		}

		return result;
	}

	public static CombinedCharacteristicSpecification createCombinedCS(String code, SelectionCharacteristicSpecification primary, SelectionCharacteristicSpecification secondary,
			Map<Integer, List<Integer>> relatedOptionsMap) {

		CombinedCharacteristicSpecification result = new CombinedCharacteristicSpecification();
		result.setCode(code);
		result.setName(code);
		result.setPrimary(primary);
		result.setSecondary(secondary);
		if (relatedOptionsMap != null) {
			for (Integer key : relatedOptionsMap.keySet()) {
				SpecOption primarySo = primary.getOptions().get(key);
				List<Integer> values = relatedOptionsMap.get(key);
				if (values != null) {
					SpecOptionCombinedRelation relation = new SpecOptionCombinedRelation();
					relation.setPrimary(primarySo);

					for (Integer optionOrder : values) {
						SpecOptionRelatedOptions relatedOption = new SpecOptionRelatedOptions();
						relatedOption.setOption(secondary.getOptions().get(optionOrder));
						relation.getRelatedOptions().add(relatedOption);
					}

					result.getRelatedOptions().put(primarySo, relation);
				}
			}
		}

		return result;

	}

	public static ConditionalCharacteristicSpecification createConditionalCS(String code, SelectionCharacteristicSpecification primary,
			Map<SpecOption, List<AtomicCharacteristicSpecification>> relatedCharacteristicsMap) {

		ConditionalCharacteristicSpecification result = new ConditionalCharacteristicSpecification();
		result.setCode(code);
		result.setName(code);
		result.setPrimary(primary);

		if (relatedCharacteristicsMap != null) {
			for (SpecOption key : relatedCharacteristicsMap.keySet()) {

				List<AtomicCharacteristicSpecification> values = relatedCharacteristicsMap.get(key);
				if (values != null) {
					SpecOptionConditionalRelation relation = new SpecOptionConditionalRelation();
					relation.setPrimary(key);

					for (AtomicCharacteristicSpecification acs : values) {
						SpecOptionConditionalCharacteristic socc = new SpecOptionConditionalCharacteristic();
						socc.setCharacteristic(acs);
						relation.getConditionalCharacteristics().add(socc);
					}

					result.getConditionalCharacteristics().put(key, relation);
				}
			}
		}

		return result;

	}
}
