package org.itasyurt.karaf.core.characteristic.specification.conditional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.itasyurt.karaf.core.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.core.entity.BaseEntity;

@Entity
public class SpecOptionConditionalRelation extends BaseEntity {

	@ManyToOne
	private SpecOption primary;
	
	@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
	@JoinColumn
	@OrderColumn(name="ix")
	private List<SpecOptionConditionalCharacteristic> conditionalCharacteristics= new ArrayList<SpecOptionConditionalCharacteristic>();

	public SpecOption getPrimary() {
		return primary;
	}

	public void setPrimary(SpecOption primary) {
		this.primary = primary;
	}

	public List<SpecOptionConditionalCharacteristic> getConditionalCharacteristics() {
		return conditionalCharacteristics;
	}

	public void setConditionalCharacteristics(List<SpecOptionConditionalCharacteristic> conditionalCharacteristics) {
		this.conditionalCharacteristics = conditionalCharacteristics;
	} 
	
	
}
