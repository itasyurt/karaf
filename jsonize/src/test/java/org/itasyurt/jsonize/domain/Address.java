package org.itasyurt.jsonize.domain;

import org.itasyurt.jsonize.annotations.JsonSummary;

public class Address extends BaseEntity {

	@JsonSummary
	private String addressText;

	@JsonSummary
	private City city;

	public String getAddressText() {
		return addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
