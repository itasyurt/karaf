package org.itasyurt.jsonize.domain.football;

import java.util.HashSet;
import java.util.Set;

import org.itasyurt.jsonize.annotations.JsonSummary;

public class Tournament extends BaseEntity {

	@JsonSummary
	private Set<Club> clubs = new HashSet<Club>();

	public Set<Club> getClubs() {
		return clubs;
	}

	public void setClubs(Set<Club> clubs) {
		this.clubs = clubs;
	}
	
	
}
