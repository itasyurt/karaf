package org.itasyurt.karaf.domain.characteristic.specification.combined;

import java.util.HashMap;
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
public class CombinedCharacteristicSpecification extends CharacteristicSpecification {

	@ManyToOne
	private SelectionCharacteristicSpecification primary;

	@ManyToOne
	private SelectionCharacteristicSpecification secondary;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	@MapKey(name = "primary")
	private Map<SpecOption, SpecOptionCombinedRelation> relatedOptions = new HashMap<SpecOption,SpecOptionCombinedRelation>();

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

	public Map<SpecOption, SpecOptionCombinedRelation> getRelatedOptions() {
		return relatedOptions;
	}

	public void setRelatedOptions(Map<SpecOption, SpecOptionCombinedRelation> relatedOptions) {
		this.relatedOptions = relatedOptions;
	}
	
	

	

}
