package org.itasyurt.jsonize.domain.football;

import java.util.ArrayList;
import java.util.List;

import org.itasyurt.jsonize.annotations.JsonDetail;

public class MatchScore {

	@JsonDetail
	private List<GoalInfo> homeGoalInfo = new ArrayList<GoalInfo>();
	@JsonDetail
	private List<GoalInfo> awayGoalInfo = new ArrayList<GoalInfo>();

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
