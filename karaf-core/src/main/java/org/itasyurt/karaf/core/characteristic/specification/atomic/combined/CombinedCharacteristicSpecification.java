package org.itasyurt.karaf.core.characteristic.specification.atomic.combined;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import org.itasyurt.karaf.core.characteristic.specification.atomic.CharacteristicSpecification;
import org.itasyurt.karaf.core.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.core.characteristic.specification.atomic.SpecOption;

@Entity
public class CombinedCharacteristicSpecification extends CharacteristicSpecification {

	@ManyToOne
	private SelectionCharacteristicSpecification primary;

	@ManyToOne
	private SelectionCharacteristicSpecification secondary;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	@MapKey(name = "option")
	private Map<SpecOption, SpecOptionRelatedOptions> relatedOptions = new HashMap<SpecOption,SpecOptionRelatedOptions>();

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

	

}
