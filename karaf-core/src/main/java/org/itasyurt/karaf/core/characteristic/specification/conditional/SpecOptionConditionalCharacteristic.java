package org.itasyurt.karaf.core.characteristic.specification.conditional;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.itasyurt.karaf.core.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.core.entity.BaseEntity;

@Entity
public class SpecOptionConditionalCharacteristic extends BaseEntity {

	@ManyToOne
	private AtomicCharacteristicSpecification characteristic;

	public AtomicCharacteristicSpecification getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(AtomicCharacteristicSpecification characteristic) {
		this.characteristic = characteristic;
	}

}
