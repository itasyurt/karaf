package org.itasyurt.karaf.domain.characteristic.specification.atomic;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("DCS")
public class DateCharacteristicSpecification extends AtomicCharacteristicSpecification {

	@Temporal(TemporalType.TIMESTAMP)
	private Date minDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date maxDate;

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

}