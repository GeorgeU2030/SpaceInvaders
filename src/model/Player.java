package model;

public class Player {
	public String username;

	public int totalScore;
	public Player(String username, int totalScore) {
		super();
		this.username = username;
		this.totalScore = totalScore;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	
}
