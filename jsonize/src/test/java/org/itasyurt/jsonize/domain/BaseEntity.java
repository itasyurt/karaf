package org.itasyurt.jsonize.domain;

import org.itasyurt.jsonize.annotations.JsonDetail;

public class BaseEntity {

	@JsonDetail
	protected String id;
	@JsonDetail
	protected String name;
	
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
	
	
}
