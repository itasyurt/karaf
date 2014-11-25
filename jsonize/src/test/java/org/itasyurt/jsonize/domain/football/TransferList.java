package org.itasyurt.jsonize.domain.football;

import java.util.ArrayList;
import java.util.List;

import org.itasyurt.jsonize.annotations.JsonSummary;

public class  TransferList extends BaseEntity{

	
	@JsonSummary
	private List<Person> players =new ArrayList<Person>();

	public List<Person> getPlayers() {
		return players;
	}

	public void setPlayers(List<Person> players) {
		this.players = players;
	}
	
	
	
	
}
