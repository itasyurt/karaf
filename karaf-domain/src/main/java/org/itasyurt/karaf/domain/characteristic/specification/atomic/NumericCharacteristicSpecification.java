package org.itasyurt.karaf.domain.characteristic.specification.atomic;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class NumericCharacteristicSpecification  extends AtomicCharacteristicSpecification{

	private BigDecimal min;

	private BigDecimal max;

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

}