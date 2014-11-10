package org.itasyurt.karaf.domain.characteristic.specification.conditional;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import org.itasyurt.karaf.domain.characteristic.specification.atomic.CharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;

@Entity
public class ConditionalCharacteristicSpecification extends CharacteristicSpecification {

	@ManyToOne
	private SelectionCharacteristicSpecification primary;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	@MapKey(name = "primary")
	private Map<SpecOption, SpecOptionConditionalRelation> conditionalCharacteristics;

	public SelectionCharacteristicSpecification getPrimary() {
		return primary;
	}

	public void setPrimary(SelectionCharacteristicSpecification primary) {
		this.primary = primary;
	}

	public Map<SpecOption, SpecOptionConditionalRelation> getConditionalCharacteristics() {
		return conditionalCharacteristics;
	}

	public void setConditionalCharacteristics(Map<SpecOption, SpecOptionConditionalRelation> conditionalCharacteristics) {
		this.conditionalCharacteristics = conditionalCharacteristics;
	}

	

}
