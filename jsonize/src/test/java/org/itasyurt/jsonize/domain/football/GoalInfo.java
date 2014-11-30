package org.itasyurt.jsonize.domain.football;

import org.itasyurt.jsonize.annotations.JsonSummary;

public class GoalInfo {

	@JsonSummary
	private  Person player;
	@JsonSummary
	private Integer minute;
	
	@JsonSummary
	private GoalType goalType;
	
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
	public GoalType getGoalType() {
		return goalType;
	}
	public void setGoalType(GoalType goalType) {
		this.goalType = goalType;
	}
	
}
