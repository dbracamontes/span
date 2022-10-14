package com.span.challenge.model;

import com.span.challenge.enums.GameResultEnum;

/**
 * 
 * @author daniel
 *
 */
public class GameDetails {
	private String localTeam;
	private Integer localTeamScore;
	private String visitorTeam;
	private Integer visitorTeamScore;
	private GameResultEnum gameResult;

	public String getLocalTeam() {
		return localTeam;
	}

	public void setLocalTeam(String localTeam) {
		this.localTeam = localTeam;
	}

	public Integer getLocalTeamScore() {
		return localTeamScore;
	}

	public void setLocalTeamScore(Integer localTeamScore) {
		this.localTeamScore = localTeamScore;
	}

	public String getVisitorTeam() {
		return visitorTeam;
	}

	public void setVisitorTeam(String visitorTeam) {
		this.visitorTeam = visitorTeam;
	}

	public Integer getVisitorTeamScore() {
		return visitorTeamScore;
	}

	public void setVisitorTeamScore(Integer visitorTeamScore) {
		this.visitorTeamScore = visitorTeamScore;
	}

	public GameResultEnum getGameResult() {
		return gameResult;
	}

	public void setGameResult(GameResultEnum gameResult) {
		this.gameResult = gameResult;
	}

	@Override
	public String toString() {
		return "GameDetails [localTeam=" + localTeam + ", localTeamScore=" + localTeamScore + ", visitorTeam="
				+ visitorTeam + ", visitorTeamScore=" + visitorTeamScore + ", gameResult=" + gameResult + "]";
	}

}
