package org.itasyurt.karaf.domain.characteristic.specification.atomic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.itasyurt.jsonize.annotations.JsonDetail;
import org.itasyurt.jsonize.annotations.JsonSubtype;

@Entity
@DiscriminatorValue("TCS")
@JsonSubtype
public class TextCharacteristicSpecification extends
		AtomicCharacteristicSpecification {

	@JsonDetail
	private Integer minLength = 0;

	@JsonDetail
	private Integer maxLength = 255;

	@JsonDetail
	private String regexPattern;

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String getRegexPattern() {
		return regexPattern;
	}

	public void setRegexPattern(String regexPattern) {
		this.regexPattern = regexPattern;
	}

}
