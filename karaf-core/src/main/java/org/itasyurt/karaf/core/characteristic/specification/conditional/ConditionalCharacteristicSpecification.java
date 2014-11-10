package org.itasyurt.karaf.core.characteristic.specification.conditional;

import java.util.List;
import java.util.Map;

import org.itasyurt.karaf.core.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.core.characteristic.specification.atomic.CharacteristicSpecification;
import org.itasyurt.karaf.core.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.core.characteristic.specification.atomic.SpecOption;

public class ConditionalCharacteristicSpecification extends CharacteristicSpecification {

	private SelectionCharacteristicSpecification primary;
	private Map<SpecOption, List<AtomicCharacteristicSpecification>> conditionalCharacteristics;

	public SelectionCharacteristicSpecification getPrimary() {
		return primary;
	}

	public void setPrimary(SelectionCharacteristicSpecification primary) {
		this.primary = primary;
	}

	public Map<SpecOption, List<AtomicCharacteristicSpecification>> getConditionalCharacteristics() {
		return conditionalCharacteristics;
	}

	public void setConditionalCharacteristics(Map<SpecOption, List<AtomicCharacteristicSpecification>> conditionalCharacteristics) {
		this.conditionalCharacteristics = conditionalCharacteristics;
	}
	
	
	
}
