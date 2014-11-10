package org.itasyurt.karaf.core.characteristic.specification.atomic;

import java.util.List;

public class SelectionCharacteristicSpecification  extends AtomicCharacteristicSpecification{

	private List<SpecOption> options;

	public List<SpecOption> getOptions() {
		return options;
	}

	public void setOptions(List<SpecOption> options) {
		this.options = options;
	}

}
