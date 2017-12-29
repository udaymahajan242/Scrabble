
public class Player {
	int score;
	Letter[] tray;
	
	public Player() {
		this.score = 0;
		this.tray = new Letter[7];
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int s) {
		score = s;
	}	
}