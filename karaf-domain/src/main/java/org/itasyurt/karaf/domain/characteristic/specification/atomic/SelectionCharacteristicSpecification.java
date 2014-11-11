package org.itasyurt.karaf.domain.characteristic.specification.atomic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
@DiscriminatorValue("SCS")
public class SelectionCharacteristicSpecification extends AtomicCharacteristicSpecification {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	@OrderColumn(name = "ix")
	private List<SpecOption> options = new ArrayList<SpecOption>();

	public List<SpecOption> getOptions() {
		return options;
	}

	public void setOptions(List<SpecOption> options) {
		this.options = options;
	}

}
