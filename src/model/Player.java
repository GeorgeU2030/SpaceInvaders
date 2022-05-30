package model;

public class Player {
	public String username;
	public int score=0;
	public long totalScore;
	public Player(String username, int score, long totalScore) {
		super();
		this.username = username;
		this.score +=score;
		this.totalScore = totalScore;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public long getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(long totalScore) {
		this.totalScore = totalScore;
	}
	
	
}
