
public class Pouch {
	public int pouchLettersLeft;
	public static Letter[] pouch;
	
	public Pouch() {
		initializePouch();
	}

	public void drawLetters(Letter[] tray) {
		// Draw random letters from the pouch to replace the used tray letters.
		for (int i = 0; i < 7; i++) {
			if (getPouchLettersLeft() > 0) {
				if (tray[i] == null || !Scrabble.trayField[i].isEnabled()) {	//
					int j = (int) (Math.random() * getPouchLettersLeft());
					tray[i] = pouch[j];
					shrinkPouch(j);
				} 
			} else if (!Scrabble.trayField[i].isEnabled()) tray[i] = null;
		}
	}
	
	public void displayLettersInTrayfield(Letter[] tray) {
		for (int i = 0; i < 7; i++) {
			if(tray[i] != null) {
				Scrabble.trayField[i].setText(tray[i].getDisplay());
				Scrabble.trayField[i].setEnabled(true);
			} else {
				Scrabble.trayField[i].setText("");
				Scrabble.trayField[i].setEnabled(false);
			}
		}
	}

	public void shrinkPouch(int replaceLocation) {
		// Move the letters in the pouch array up, to take the place of the letter that was sent to the tray.
		int i;
		for (i = replaceLocation; i < getPouchLettersLeft() - 1; i++) {
			pouch[i] = Letter.clone(pouch[i + 1]);
		}
		pouch[i] = null;
		setPouchLettersLeft(getPouchLettersLeft() - 1);
	}

	public int getPouchLettersLeft() {
		return pouchLettersLeft;
	}

	public void setPouchLettersLeft(int i) {
		pouchLettersLeft = i;
	}

	public void initializePouch() {
		pouch = new Letter[98];
		setPouchLettersLeft(98);
		int i;
		for (i = 0; i < 9; i++) {
			pouch[i] = new Letter('A', 1, "A\u2081"); 
		}
		for (i = 9; i < 11; i++) {
			pouch[i] = new Letter('B', 3, "B\u2083");
		}
		for (i = 11; i < 13; i++) {
			pouch[i] = new Letter('C', 3, "C\u2083");
		}
		for (i = 13; i < 17; i++) {
			pouch[i] = new Letter('D', 2, "D\u2082");
		}
		for (i = 17; i < 29; i++) {
			pouch[i] = new Letter('E', 1, "E\u2081");
		}
		for (i = 29; i < 31; i++) {
			pouch[i] = new Letter('F', 4, "F\u2084");
		}
		for (i = 31; i < 34; i++) {
			pouch[i] = new Letter('G', 2, "G\u2082");
		}
		for (i = 34; i < 36; i++) {
			pouch[i] = new Letter('H', 4, "H\u2084");
		}
		for (i = 36; i < 45; i++) {
			pouch[i] = new Letter('I', 1, "I\u2081");
		}
		for (i = 45; i < 46; i++) {
			pouch[i] = new Letter('J', 8, "J\u2088");
		}
		for (i = 46; i < 47; i++) {
			pouch[i] = new Letter('K', 5, "K\u2085");
		}
		for (i = 47; i < 51; i++) {
			pouch[i] = new Letter('L', 1, "L\u2081");
		}
		for (i = 51; i < 53; i++) {
			pouch[i] = new Letter('M', 3, "M\u2083");
		}
		for (i = 53; i < 59; i++) {
			pouch[i] = new Letter('N', 1, "N\u2081");
		}
		for (i = 59; i < 67; i++) {
			pouch[i] = new Letter('O', 1, "O\u2081");
		}
		for (i = 67; i < 69; i++) {
			pouch[i] = new Letter('P', 3, "P\u2083");
		}
		for (i = 69; i < 70; i++) {
			pouch[i] = new Letter('Q', 10, "Q\u2081\u2080");
		}
		for (i = 70; i < 76; i++) {
			pouch[i] = new Letter('R', 1, "R\u2081");
		}
		for (i = 76; i < 80; i++) {
			pouch[i] = new Letter('S', 1, "S\u2081");
		}
		for (i = 80; i < 86; i++) {
			pouch[i] = new Letter('T', 1, "T\u2081");
		}
		for (i = 86; i < 90; i++) {
			pouch[i] = new Letter('U', 1, "U\u2081");
		}
		for (i = 90; i < 92; i++) {
			pouch[i] = new Letter('V', 4, "V\u2084");
		}
		for (i = 92; i < 94; i++) {
			pouch[i] = new Letter('W', 4, "W\u2084");
		}
		for (i = 94; i < 95; i++) {
			pouch[i] = new Letter('X', 8, "X\u2088");
		}
		for (i = 95; i < 97; i++) {
			pouch[i] = new Letter('Y', 4, "Y\u2084");
		}
		for (i = 97; i < 98; i++) {
			pouch[i] = new Letter('Z', 10, "Z\u2081\u2080"); 
		}
	}
}
