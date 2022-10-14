package com.span.challenge.model;

/**
 * 
 * @author daniel
 *
 */
public class TeamInfo {

	private String name;
	private int points;
	

	public TeamInfo(String name, Integer points) {
		this.name = name;
		this.points = points;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}


	@Override
	public String toString() {
		return "TeamInfo [name=" + name + ", points=" + points + "]";
	}
	
}
