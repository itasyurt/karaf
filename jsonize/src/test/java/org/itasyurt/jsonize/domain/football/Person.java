package org.itasyurt.jsonize.domain.football;

import java.util.ArrayList;
import java.util.List;

import org.itasyurt.jsonize.annotations.JsonDetail;
import org.itasyurt.jsonize.annotations.JsonSummary;

public class Person extends BaseEntity {

	@JsonSummary
	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
