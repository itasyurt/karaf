package org.itasyurt.jsonize.domain;

import org.itasyurt.jsonize.annotations.JsonSummary;

public class Country extends BaseEntity {

	@JsonSummary
	private Association association;

	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}

}
