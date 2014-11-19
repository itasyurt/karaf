package org.itasyurt.jsonize.domain;

import java.util.ArrayList;
import java.util.List;

public class MatchScore {

	private List<GoalInfo> homeGoalInfo= new ArrayList<GoalInfo>();
	private List<GoalInfo> awayGoalInfo=new ArrayList<GoalInfo>();
	
	public List<GoalInfo> getHomeGoalInfo() {
		return homeGoalInfo;
	}
	public void setHomeGoalInfo(List<GoalInfo> homeGoalInfo) {
		this.homeGoalInfo = homeGoalInfo;
	}
	public List<GoalInfo> getAwayGoalInfo() {
		return awayGoalInfo;
	}
	public void setAwayGoalInfo(List<GoalInfo> awayGoalInfo) {
		this.awayGoalInfo = awayGoalInfo;
	}
	
	
}
