package org.itasyurt.karaf.domain.characteristic.specification.combined;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.itasyurt.jsonize.annotations.JsonSummary;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.entity.BaseEntity;

@Entity
public class SpecOptionRelatedOptions  extends BaseEntity{

	@ManyToOne
	@JsonSummary
	private SpecOption option;

	public SpecOption getOption() {
		return option;
	}

	public void setOption(SpecOption option) {
		this.option = option;
	}

	@Override
	protected Object[] equalsFields() {
		
		return null;
	}

	@Override
	protected Object[] hashCodeFields() {
		
		return null;
	}
	
	
}
