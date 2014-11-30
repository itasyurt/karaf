package org.itasyurt.jsonize.domain.football;

import java.util.Date;

import org.itasyurt.jsonize.annotations.JsonDetail;
import org.itasyurt.jsonize.annotations.JsonSummary;

public class Match extends BaseEntity {

	@JsonSummary
	private Date matchDate;

	@JsonSummary
	private Club homeTeam;
	@JsonSummary
	private Club awayTeam;
	@JsonSummary
	private Stadium stadium;
	@JsonDetail
	private MatchScore score;
	@JsonSummary
	private MatchType matchType;

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

	public MatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(MatchType matchType) {
		this.matchType = matchType;
	}

}
