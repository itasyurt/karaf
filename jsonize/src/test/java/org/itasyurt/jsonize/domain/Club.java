package org.itasyurt.jsonize.domain;

import java.util.List;

public class Club {

	private String id;
	private String name;
	private Country country;
	private List<Person> players;
	private Person manager;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
