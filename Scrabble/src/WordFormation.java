import java.io.IOException;
import java.util.ArrayList;

//note: when implementing this. if it returns an arraylist of size 0 then nothing passed
//I still need to add in a check if words are 

//implement JOptionPane.showMessageDialog(frame, "The word does not exist. Please try again!");
//btnUndo.doClick();

public class WordFormation {
	static ArrayList<WordDim> words;
	static ArrayList<WordDim> checkedWords;
	static ArrayList<String> finalWords;

	public static boolean checkAlone(GridBit oldField, GridBit newField) {
		//returns false if a word is alone
		//returns true if a word isn't alone
		for (int xPos = 0; xPos < 15; xPos++) {
			for (int yPos = 0; yPos < 15; yPos++) {
				if (oldField.getSquareLetter(xPos, yPos) == null 
						&& newField.getSquareLetter(xPos, yPos) != null) {					
					//checks corners
					//checks top left corner for loners
					if (xPos == 0 && yPos == 0) {
						if (newField.getSquareLetter(xPos, yPos+1) == null) return true;
						else if (newField.getSquareLetter(xPos+1, yPos) == null) return true;
					}
					//checks top right corner for loners
					if (xPos == 0 && yPos == 14) {
						if (newField.getSquareLetter(xPos, yPos-1) == null) return true;
						else if (newField.getSquareLetter(xPos+1, yPos) == null) return true;
					}
					//checks bottom left corner for loners
					if (xPos == 14 && yPos == 0) {
						System.out.println("making it here!!!?");
						if (newField.getSquareLetter(xPos, yPos+1) == null) return true;
						else if (newField.getSquareLetter(xPos-1, yPos) == null) return true;
					}
					//checks bottom right corner for loners
					if (xPos == 14 && yPos == 14) {
						if (newField.getSquareLetter(xPos, yPos-1) == null) return true;
						else if (newField.getSquareLetter(xPos-1, yPos) == null) return true;
					}
					//check borders
					//checks left border for loners
					if (yPos == 0 && xPos > 0 && xPos < 14) {
						if (newField.getSquareLetter(xPos, yPos+1) == null) return true;
						else if (newField.getSquareLetter(xPos+1, yPos) == null) return true;
						else if (newField.getSquareLetter(xPos-1, yPos) == null) return true;
					}
					//checks right border for loners
					if (yPos == 14 && xPos > 0 && xPos < 14) {
						if (newField.getSquareLetter(xPos, yPos-1) == null) return true;
						else if (newField.getSquareLetter(xPos+1, yPos) == null) return true;
						else if (newField.getSquareLetter(xPos-1, yPos) == null) return true;
					}
					//checks top border for loners
					if (xPos == 0 && yPos > 0 && yPos < 14) {
						if (newField.getSquareLetter(xPos, yPos+1) == null) return true;
						else if (newField.getSquareLetter(xPos, yPos-1) == null) return true;
						else if (newField.getSquareLetter(xPos++, yPos) == null) return true;
					}
					//checks bottom border for loners
					if (xPos == 0 && yPos > 0 && yPos < 14) {
						if (newField.getSquareLetter(xPos, yPos-1) == null) return true;
						else if (newField.getSquareLetter(xPos+1, yPos) == null) return true;
						else if (newField.getSquareLetter(xPos-1, yPos) == null) return true;
					}
					if (xPos > 0 && xPos < 14 && yPos > 0 && yPos < 14) {
						if (newField.getSquareLetter(xPos+1, yPos) == null && newField.getSquareLetter(xPos-1, yPos) == null
							&& newField.getSquareLetter(xPos, yPos+1) == null && newField.getSquareLetter(xPos, yPos-1) == null) return true;
					}					
				}
			}
		}
		return false;		
	}

	public static String buildY(int minY, int maxY, int xPos, GridBit newField) {
		String word = "";
		while (minY <= maxY) {
			word = word + Character.toString(newField.getSquareLetter(minY, xPos).getLetter());
			minY++;
		}
		return word;
	}

	public static String buildX(int minX, int maxX, int yPos, GridBit newField) {
		String word = "";
		while (minX <= maxX) {
			word = word + Character.toString(newField.getSquareLetter(yPos, minX).getLetter());
			minX++;
		}
		return word;
	}

	public static boolean checkWord(String word) throws IOException {
		WordList wl = new WordList();
		return wl.wordExists(word);
	}

	// oldField is previous.. newField is gridbit... because the board's
	// previous state is what you are comparing
	// the new letters or word against
	public static ArrayList<String> possibleWords(GridBit oldField, GridBit newField) {
		words = new ArrayList<WordDim>();
		checkedWords = new ArrayList<WordDim>();
		finalWords = new ArrayList<String>();

		// builds every possible new word...

		// currently gets stuck in a while loop..
		for (int xPos = 0; xPos < 15; xPos++) {
			for (int yPos = 0; yPos < 15; yPos++) {
				// this executes if a NEW LETTER is on the board
				if (newField.getSquareLetter(xPos, yPos) != null && oldField.getSquareLetter(xPos, yPos) == null) {

					int minX = xPos;
					int maxX = xPos;
					int minY = yPos;
					int maxY = yPos;

					// checks to the left of the character...
					if (minX > 0 && newField.getSquareLetter(minX - 1, yPos) != null) {
						while (newField.getSquareLetter(minX - 1, yPos) != null) {
							System.out.print(newField.getSquareLetter(minX, yPos).getLetter());
							minX--;
							System.out.println(" " + minX + " " + yPos);
						}
					}
					// checks to the right of the character...
					if (maxX < 14 && newField.getSquareLetter(maxX + 1, yPos) != null) {
						while (newField.getSquareLetter(maxX + 1, yPos) != null) {
							System.out.println(newField.getSquareLetter(maxX + 1, yPos).getLetter());

							maxX++;
							System.out.println(maxX + " " + yPos);
						}

					}
					// checks above the character...
					if (minY > 0 && newField.getSquareLetter(xPos, minY - 1) != null) {
						while (newField.getSquareLetter(xPos, minY - 1) != null) {
							System.out.print(newField.getSquareLetter(xPos, minY - 1).getLetter());
							minY--;
							System.out.println(" " + xPos + " " + minY);

						}

					}
					// checks below the character...
					// y is inverted on our board
					if (maxY < 14) {
						System.out.println("mark1");
						if (newField.getSquareLetter(xPos, maxY + 1) != null) {
							System.out.println("mark2");
							while (newField.getSquareLetter(xPos, maxY + 1) != null) {
								System.out.print(newField.getSquareLetter(xPos, maxY + 1).getLetter());
								maxY++;
								System.out.println(" " + minX + " " + maxY);

							}
						}
					}

					if (minX == maxX && minY == maxY)
						System.out.println("one letter played/ or alone");

					// add all words to an array and check at the end
					// intersection check
					if (minX != maxX && minY != maxY) {
						if (xPos != minX && xPos != maxX) {
							if (yPos != minY && yPos != maxY) {
								System.out.println("Word's are not connected");
							}
						} else {
							// build x and y.. print both out

							String xIt = buildX(minY, maxY, xPos, newField);
							words.add(new WordDim(minX, maxX, minY, maxY, xIt));
							String yIt = buildY(minX, maxX, yPos, newField);

							words.add(new WordDim(minX, maxX, minY, maxY, yIt));

							System.out.println("there apparantley are intercepting words..");
							System.out.println("x is: " + xIt);
							System.out.println("y is: " + yIt);
						}
					}
					// word is going horizontally
					if (minX == maxX && minY != maxY) {
						String xWord = buildX(minY, maxY, xPos, newField);
						System.out.println("hor. word is: " + xWord);
						words.add(new WordDim(minX, maxX, minY, maxY, xWord));
					}
					// word is going vertically
					if (minX != maxX && minY == maxY) {
						String yWord = buildY(minX, maxX, yPos, newField);
						System.out.println("vert word is: " + yWord);
						words.add(new WordDim(minX, maxX, minY, maxY, yWord));

					}

					for (int i = 0; i < words.size(); i++) {
						System.out.println("looping new words: " + words.get(i).getWord());

					}

				}
			}

		}
		sortWords();
		convert();
		return finalWords;
	}

	private static void sortWords() {

		// this is where the code was giving the null pointer exception...

		// first step is to make sure there is only 1 copy of each word per xx
		// yy cordinates

		for (int t = 0; t < words.size(); t++) {
			if (checkedWords.size() == 0) {
				checkedWords.add(new WordDim(words.get(t).getMinX(), words.get(t).getMaxX(), words.get(t).getMinY(),
						words.get(t).getMaxY(), words.get(t).getWord()));
			}
			if (checkedWords.size() > 0) {
				for (int v = 0; v < checkedWords.size(); v++) {
					if (words.get(t).getWord().equals(checkedWords.get(v).getWord())) {
						System.out.println("Same word detected: ");
						if (words.get(t).getMinX() == checkedWords.get(v).getMinX()
								&& words.get(t).getMaxX() == checkedWords.get(v).getMaxX()
								&& words.get(t).getMinY() == checkedWords.get(v).getMinY()
								&& words.get(t).getMaxY() == checkedWords.get(v).getMaxY()) {
							// same exact word
						} else
							checkedWords.add(new WordDim(words.get(t).getMinX(), words.get(t).getMaxX(),
									words.get(t).getMinY(), words.get(t).getMaxY(), words.get(t).getWord()));

						// add same word dif location..
					} else
						checkedWords.add(new WordDim(words.get(t).getMinX(), words.get(t).getMaxX(),
								words.get(t).getMinY(), words.get(t).getMaxY(), words.get(t).getWord()));
				}
			}
		}
		if (checkedWords.size() > 1) {
			for (int i = 0; i > checkedWords.size(); i++) {
				System.out.println("in checked: " + checkedWords.get(i).getWord());
			}
		}
	}

	private static void convert() {
		System.out.println("words that passed:");
		for (int i = 0; i < checkedWords.size(); i++) {
			finalWords.add(checkedWords.get(i).getWord());
			System.out.println(finalWords.get(i));

		}
	}
}