package org.itasyurt.jsonize.domain.football;

import org.itasyurt.jsonize.annotations.JsonDetail;
import org.itasyurt.jsonize.annotations.JsonSummary;

public class Stadium extends BaseEntity {

	@JsonSummary
	private City city;
	
	@JsonSummary 
	private StadiumStatus status;
	
	
	@JsonDetail
	private Address address;

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public StadiumStatus getStatus() {
		return status;
	}

	public void setStatus(StadiumStatus status) {
		this.status = status;
	}
	
	

}
