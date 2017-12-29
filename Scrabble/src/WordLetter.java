
public class WordLetter {
	private String letter;
	private int x;
	private int y;
	private int letterVal;
	private int wordVal;
	
	public WordLetter(String letter, int x, int y,  int lVal, int wVal) {
		this.letter = letter;
		this.x = x;
		this.y = y;
		this.letterVal = lVal;
		this.wordVal = wVal;
	}
	
	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLetterVal() {
		return letterVal;
	}

	public void setLetterVal(int letterVal) {
		this.letterVal = letterVal;
	}

	public int getWordVal() {
		return wordVal;
	}

	public void setWordVal(int wordVal) {
		this.wordVal = wordVal;
	}
	
	public String toString() {
		return letter + " (" + x + ", " + y + ") " + letterVal + " " + wordVal;
	}
}
