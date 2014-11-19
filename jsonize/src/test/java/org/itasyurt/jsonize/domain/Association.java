package org.itasyurt.jsonize.domain;

import org.itasyurt.jsonize.annotations.JsonDetail;

public class Association extends BaseEntity {

	@JsonDetail
	private Integer foundationYear;

	public Integer getFoundationYear() {
		return foundationYear;
	}

	public void setFoundationYear(Integer foundationYear) {
		this.foundationYear = foundationYear;
	}

}
