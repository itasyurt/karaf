package org.itasyurt.jsonize.domain.football;

import org.itasyurt.jsonize.annotations.JsonSummary;

public class City extends BaseEntity {

	@JsonSummary
	private String code;
	@JsonSummary
	private Country country;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
