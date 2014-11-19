package org.itasyurt.jsonize.domain;

import java.util.ArrayList;
import java.util.List;

import org.itasyurt.jsonize.annotations.JsonDetail;

public class Person extends BaseEntity {

	@JsonDetail
	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
