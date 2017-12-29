public class GridBit {

	private int[][] bonusLetters;
	private int[][] bonusWords;
	private Letter[][] squareLetter; 														


	public GridBit(int xDim, int yDim) {
		bonusLetters = new int[xDim][yDim];
		bonusWords = new int[xDim][yDim];
		squareLetter = new Letter[xDim][yDim];
	}

	public int getBonusLetter(int xPos, int yPos) {
		return bonusLetters[xPos][yPos];
	}
	
	public int getBonusWord(int xPos, int yPos) {
		return bonusWords[xPos][yPos];
	}
	
	public Letter getSquareLetter(int xPos, int yPos) {					
		return squareLetter[xPos][yPos];													
	}																																									//
	
	public void setBonusWord(int xPos, int yPos, int bonusWord) {
		bonusWords[xPos][yPos] = bonusWord;
	}
	
	public void setBonusLetter(int xPos, int yPos, int bonusLetter) {
		bonusLetters[xPos][yPos] = bonusLetter;
	}
	
	public void setSquareLetter(int xPos, int yPos, Letter l) {		//
		squareLetter[xPos][yPos] = l;								//
	}				//
}
