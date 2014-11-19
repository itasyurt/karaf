package org.itasyurt.jsonize.domain;

import java.util.List;

import org.itasyurt.jsonize.annotations.JsonDetail;
import org.itasyurt.jsonize.annotations.JsonSummary;

public class Club extends BaseEntity {

	@JsonSummary
	private Country country;
	@JsonDetail
	private List<Person> players;
	@JsonDetail
	private Person manager;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Person> getPlayers() {
		return players;
	}

	public void setPlayers(List<Person> players) {
		this.players = players;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

}
