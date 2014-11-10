package org.itasyurt.karaf.core.characteristic.specification.atomic.combined;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.itasyurt.karaf.core.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.core.entity.BaseEntity;

@Entity
public class SpecOptionRelatedOptions  extends BaseEntity{

	@ManyToOne
	private SpecOption option;

	public SpecOption getOption() {
		return option;
	}

	public void setOption(SpecOption option) {
		this.option = option;
	}
	
	
}
