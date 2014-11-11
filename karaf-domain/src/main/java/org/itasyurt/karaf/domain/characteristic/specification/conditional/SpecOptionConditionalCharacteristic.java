package org.itasyurt.karaf.domain.characteristic.specification.conditional;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.domain.entity.BaseEntity;

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

	@Override
	protected Object[] equalsFields() {
		// TODO Auto-generated method stub
		return new Object[]{characteristic};
	}

	@Override
	protected Object[] hashCodeFields() {
		
		return new Object[]{characteristic};
	}

}
