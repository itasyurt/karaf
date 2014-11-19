package org.itasyurt.jsonize.domain.football;

import org.itasyurt.jsonize.annotations.JsonSummary;

public class GoalInfo {

	@JsonSummary
	private  Person player;
	@JsonSummary
	private Integer minute;
	public Person getPlayer() {
		return player;
	}
	public void setPlayer(Person player) {
		this.player = player;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
}
