package org.itasyurt.jsonize.domain;

import org.itasyurt.jsonize.annotations.JsonDetail;

public class Country extends BaseEntity {

	@JsonDetail
	private Association association;

	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}

}
