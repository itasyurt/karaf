package org.itasyurt.jsonize.domain;

import java.util.Date;

public class Match {

	private String id;
	private Date matchDate;
	private Club homeTeam;
	private Club awayTeam;
	private Stadium stadium;
	private MatchScore score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public Club getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Club homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Club getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Club awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	public MatchScore getScore() {
		return score;
	}

	public void setScore(MatchScore score) {
		this.score = score;
	}
	
	

}
