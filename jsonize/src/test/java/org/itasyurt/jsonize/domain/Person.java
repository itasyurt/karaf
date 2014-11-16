package org.itasyurt.jsonize.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String id;
	private String name;
	private String surname;
	
	private List<Address> addresses= new ArrayList<Address>();
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	
	
	
}
