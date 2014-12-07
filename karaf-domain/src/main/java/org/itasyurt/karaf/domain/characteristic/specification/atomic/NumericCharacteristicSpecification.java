package org.itasyurt.karaf.domain.characteristic.specification.atomic;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.itasyurt.jsonize.annotations.JsonDetail;
import org.itasyurt.jsonize.annotations.JsonSubtype;

@Entity
@DiscriminatorValue("NCS")
@JsonSubtype
public class NumericCharacteristicSpecification  extends AtomicCharacteristicSpecification{

	@JsonDetail
	private BigDecimal min;

	@JsonDetail
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
