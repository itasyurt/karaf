package org.itasyurt.karaf.core.characteristic.specification.atomic.combined;

import java.util.List;
import java.util.Map;

import org.itasyurt.karaf.core.characteristic.specification.atomic.CharacteristicSpecification;
import org.itasyurt.karaf.core.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.core.characteristic.specification.atomic.SpecOption;

public class CombinedCharacteristicSpecification extends CharacteristicSpecification {

	private SelectionCharacteristicSpecification primary;

	private SelectionCharacteristicSpecification secondary;

	private Map<SpecOption, List<SpecOption>> relatedOptions;

	public SelectionCharacteristicSpecification getPrimary() {
		return primary;
	}

	public void setPrimary(SelectionCharacteristicSpecification primary) {
		this.primary = primary;
	}

	public SelectionCharacteristicSpecification getSecondary() {
		return secondary;
	}

	public void setSecondary(SelectionCharacteristicSpecification secondary) {
		this.secondary = secondary;
	}

	public Map<SpecOption, List<SpecOption>> getRelatedOptions() {
		return relatedOptions;
	}

	public void setRelatedOptions(Map<SpecOption, List<SpecOption>> relatedOptions) {
		this.relatedOptions = relatedOptions;
	}

}
