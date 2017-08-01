
public class Letter {

	private char letter;
	private int letterValue;
	private String display;
	
	public Letter(char letr, int value, String show) {
		this.letter = letr;
		this.letterValue = value;
		this.display = show;
	}
	
	public char getLetter() {
		return this.letter;
	}
	
	public int getLetterValue() {
		return this.letterValue;
	}	
	
	public String getDisplay() {
		return this.display;
	}
	
	public static Letter clone(Letter l) {
		char c = l.getLetter();
		int v = l.getLetterValue();
		String d = l.getDisplay();
		Letter cloneLetter = new Letter(c, v, d);
		return cloneLetter;
	}
}