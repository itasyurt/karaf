package org.itasyurt.karaf.core.characteristic.specification.atomic;

public class TextCharacteristicSpecification extends
		AtomicCharacteristicSpecification {

	private Integer minLength = 0;

	private Integer maxLength = 255;

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
