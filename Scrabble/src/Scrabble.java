import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class Scrabble {
	
	// have the data for two players stored
	// two pouchArrays, boolean toggle between

	// only thing that matters about the location is
	// if a letter is already there(done), bonus values (possibly done)

	// word formation- fill the x and y's

	JFrame frame;
	Pouch pouch;
	GridBit gb;
	GridBit previous;
	private String version;
	JPanel boardPanel;
	private int xDimension;
	private int yDimension;
	private JTextField[][] field;
	private JTextField txtPlayer1Score;
	public static JButton[] trayField;
	private String letter;
	private int place;
	private int player1score;
	private int player2score;
	private ArrayList<WordLetter> word;
	private WordLetter[] hold;
	boolean playerOnesTurn;
	private int turn;
	JButton btnPlayWord;
	JButton btnSkipTurn;
	private int skips;
	private JTextField txtPlayer2Score;
	JButton btnUndo;
	JTextField currentTurn;
	JLabel lblPlayerScore_2;
	ArrayList<String> prevText;
	ArrayList<Color> prevColor;
	JMenu mnAbout;
	JMenuItem mntmAboutScrabble;
	JMenuItem mntmHelp;
	JMenu mnAction;
	JMenuItem mntmRestart;
	JMenuItem mntmEndGame;
	JMenuBar menuBar;
	Player currentPlayer = new Player();
	Player playerOne = new Player();
	Player playerTwo = new Player();
	JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scrabble window = new Scrabble();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public Scrabble() {
		version = "17.1";
		xDimension = 15;
		yDimension = 15;
		gb = new GridBit(xDimension, yDimension);
		previous = new GridBit(xDimension, yDimension);
		updatePrevious();
		initialize();
		letter = "";
		skips = 0;
		word = new ArrayList<>();
		turn = 0;
		playerOnesTurn = true;
		pouch = new Pouch();
		pouch.drawLetters(playerOne.tray);
		pouch.displayLettersInTrayfield(playerOne.tray);
		pouch.drawLetters(playerTwo.tray);
		currentPlayer = playerOne;
		player1score = 0;
		player2score = 0;
		prevText = new ArrayList<String>();
		prevColor = new ArrayList<Color>();
	}

	private void updatePrevious() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				previous.setBonusLetter(i, j, gb.getBonusLetter(i, j));
				previous.setBonusWord(i, j, gb.getBonusWord(i, j));
				previous.setSquareLetter(i, j, gb.getSquareLetter(i, j));
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Scrabble");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(10, 10, 739, 995);
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String pathToFileOnDisk = "C:\\Users\\Uday Mahajan\\Documents\\ECLIPSE\\Scrabble\\Images\\Icon.png";
		ImageIcon img = new ImageIcon(pathToFileOnDisk);
		frame.setIconImage(img.getImage());


		JPanel boardPanel = new JPanel();
		boardPanel.setBounds(15, 26, 703, 700);
		frame.getContentPane().add(boardPanel);
		boardPanel.setLayout(new GridLayout(xDimension, yDimension));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(137, 742, 472, 50);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 7));

		JLabel lblPlayerScore_1 = new JLabel("Player 1 score");
		lblPlayerScore_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerScore_1.setBounds(79, 847, 106, 14);
		frame.getContentPane().add(lblPlayerScore_1);

		btnPlayWord = new JButton("Play word");
		btnPlayWord.addActionListener(new BtnPlayWordActionListener());
		btnPlayWord.setBounds(69, 808, 116, 23);
		frame.getContentPane().add(btnPlayWord);

		txtPlayer1Score = new JTextField();
		txtPlayer1Score.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer1Score.setEnabled(false);
		txtPlayer1Score.setText("0");
		txtPlayer1Score.setBounds(552, 877, 76, 20);
		frame.getContentPane().add(txtPlayer1Score);
		txtPlayer1Score.setColumns(10);

		btnSkipTurn = new JButton("Skip Turn");
		btnSkipTurn.addActionListener(new BtnSkipTurnActionListener());
		btnSkipTurn.setBounds(286, 808, 136, 23);
		frame.getContentPane().add(btnSkipTurn);

		txtPlayer2Score = new JTextField();
		txtPlayer2Score.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer2Score.setText("0");
		txtPlayer2Score.setEnabled(false);
		txtPlayer2Score.setColumns(10);
		txtPlayer2Score.setBounds(89, 877, 76, 20);
		frame.getContentPane().add(txtPlayer2Score);

		btnUndo = new JButton("Undo Move");
		btnUndo.addActionListener(new ButtonUndoActionListener());
		btnUndo.setBounds(528, 808, 136, 23);
		frame.getContentPane().add(btnUndo);

		lblPlayerScore_2 = new JLabel("Player 2 score");
		lblPlayerScore_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerScore_2.setBounds(542, 847, 106, 14);
		frame.getContentPane().add(lblPlayerScore_2);

		currentTurn = new JTextField();
		currentTurn.setHorizontalAlignment(SwingConstants.CENTER);
		currentTurn.setText("Player 1");
		currentTurn.setEditable(false);
		currentTurn.setBounds(319, 874, 81, 20);
		frame.getContentPane().add(currentTurn);

		JLabel lblCurrentTurn = new JLabel("Current turn");
		lblCurrentTurn.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentTurn.setBounds(309, 847, 106, 14);
		frame.getContentPane().add(lblCurrentTurn);
		String pathToEndScreenOnDisk = "C:\\Users\\Uday Mahajan\\Documents\\ECLIPSE\\Scrabble\\Images\\endScreen.jpg";
		ImageIcon Image2 = new ImageIcon(pathToEndScreenOnDisk);
		
		panel = new JPanel();
		panel.setBounds(105, 16, 10, 10);
		frame.getContentPane().add(panel);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnAction = new JMenu("Action");
		menuBar.add(mnAction);

		mntmRestart = new JMenuItem("Restart");
		mntmRestart.addActionListener(new MntmRestartActionListener());
		mnAction.add(mntmRestart);

		mntmEndGame = new JMenuItem("End Game");
		mntmEndGame.addActionListener(new MntmEndGameActionListener());
		mnAction.add(mntmEndGame);

		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		mnAbout.addActionListener(new MntmAboutScrabbleActionListener());

		mntmAboutScrabble = new JMenuItem("Info");
		mntmAboutScrabble.addActionListener(new MntmAboutScrabbleActionListener());
		mntmAboutScrabble.setHorizontalAlignment(SwingConstants.CENTER);
		mnAbout.add(mntmAboutScrabble);

		mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new MntmHelpActionListener());
		mnAbout.add(mntmHelp);

		// creates the gridLayout "field" to make the frame of the scrabble
		// board
		field = new JTextField[xDimension][yDimension];
		for (int xPos = 0; xPos < xDimension; xPos++) {
			for (int yPos = 0; yPos < yDimension; yPos++) {
				// set up field
				field[xPos][yPos] = new JTextField();
				field[xPos][yPos].setEditable(false);
				field[xPos][yPos].setVisible(true);
				field[xPos][yPos].setHorizontalAlignment(JTextField.CENTER);
				field[xPos][yPos].addMouseListener(new TextFieldActionListener(xPos, yPos));
				boardPanel.add(field[xPos][yPos]);
			}
		}

		setBackGroundColors(field);

		trayField = new JButton[7];
		for (int i = 0; i < 7; i++) {
			trayField[i] = new JButton();
			trayField[i].setVisible(true);
			trayField[i].setHorizontalAlignment(JTextField.CENTER);
			trayField[i].addActionListener(new TrayFieldActionListener(i));
			panel_1.add(trayField[i]);
		}

	}

	private void setBackGroundColors(JTextField[][] field) {
		int xHalf = xDimension / 2;
		int yHalf = yDimension / 2;
		for (int xPos = 0; xPos < xDimension; xPos++) {
			for (int yPos = 0; yPos < yDimension; yPos++) {
				// set every square to normal amount of points first
				gb.setBonusLetter(xPos, yPos, 1);
				gb.setBonusWord(xPos, yPos, 1);
				field[xPos][yPos].setBackground(new Color(240, 240, 240));
				field[xPos][yPos].setText(" ");
				// triple letter
				if (xPos % 4 == 1 && yPos % 4 == 1) {
					field[xPos][yPos].setBackground(Color.CYAN);
					field[xPos][yPos].setText("3L");
					gb.setBonusLetter(xPos, yPos, 3);
				}
				// double word
				if (xPos == yPos || xDimension - xPos == yPos + 1) {
					field[xPos][yPos].setBackground(Color.PINK);
					field[xPos][yPos].setText("2W");
					gb.setBonusWord(xPos, yPos, 2);
				}
				// double letter
				if ((xPos == 11 && yPos == 0) || (xPos == 3 && yPos == 0) || (xPos == 3 && yPos == 14)
						|| (xPos == 11 && yPos == 14) ||
						// the above 4 squares don't show up as 2L using only
						// the below code -Tony
				((xPos % 10 == 2 || yPos % 10 == 2)
						&& ((yPos == yHalf - 1 || yPos == yHalf + 1) || (xPos == xHalf - 1 || xPos == xHalf + 1)))
						|| ((xPos == xHalf || yPos == yHalf)
								&& (xPos == xHalf - xHalf / 2 - 1 || xPos == xHalf + xHalf / 2 + 1
										|| yPos == yHalf - yHalf / 2 - 1 || yPos == yHalf + yHalf / 2 + 1))
						|| ((xPos == 0 || xPos == xDimension - 1) && (yPos == 3 || yPos == 11))
						|| ((xPos == xHalf - 1 || xPos == xHalf + 1) && (yPos == yHalf - 1 || yPos == yHalf + 1))) {
					field[xPos][yPos].setBackground(Color.GREEN);
					field[xPos][yPos].setText("2L");
					gb.setBonusLetter(xPos, yPos, 2);
					gb.setBonusWord(xPos, yPos, 1);
				}
				// fill in covered up triple letters
				if ((xPos == 5 || xPos == 9) && (yPos == 5 || yPos == 9)) {
					field[xPos][yPos].setBackground(Color.CYAN);
					field[xPos][yPos].setText("3L");
					gb.setBonusLetter(xPos, yPos, 3);
					gb.setBonusWord(xPos, yPos, 1);
				}
				// triple word
				if (xPos % 7 == 0 && yPos % 7 == 0) {
					field[xPos][yPos].setBackground(Color.ORANGE);
					field[xPos][yPos].setText("3W");
					gb.setBonusWord(xPos, yPos, 3);
				}
			}
		}
		// center
		field[7][7].setBackground(Color.RED);
		field[7][7].setText("2W");
		field[7][7].setHorizontalAlignment(SwingConstants.CENTER);
		gb.setBonusWord(7, 7, 2);
	}

	// switches between player 1 and player 2
	public void swapTurn() {
		turn++;
		playerOnesTurn = !playerOnesTurn;
		word.clear();
		hold = null;
		prevText.clear();
		prevColor.clear();
		// System.out.println(playerOnesTurn);
		if (playerOnesTurn == true) {
			// System.out.println("got here");
			currentPlayer = playerOne;
			pouch.displayLettersInTrayfield(playerOne.tray);
			currentTurn.setText("Player One");
			JOptionPane.showMessageDialog(frame, "Player one's turn!");
		} else {
			currentPlayer = playerTwo;
			pouch.displayLettersInTrayfield(playerTwo.tray);
			currentTurn.setText("Player Two");
			JOptionPane.showMessageDialog(frame, "Player two's turn!");
		}
	}

	private class TrayFieldActionListener implements ActionListener {
		private int spot;

		public TrayFieldActionListener(int i) {
			spot = i;
		}

		public void actionPerformed(ActionEvent arg0) {
			// stores the letter the user choose
			place = spot;
			letter = trayField[spot].getText();
		}
	}

	private class TextFieldActionListener extends MouseAdapter {
		private int xPos;
		private int yPos;

		public TextFieldActionListener(int x, int y) {
			xPos = x;
			yPos = y;
		}

		public void mouseClicked(MouseEvent arg0) {
			if (letter.equals("")) {
				JOptionPane.showMessageDialog(frame, "Please select a letter");
			} else if (field[xPos][yPos].getBackground() == Color.LIGHT_GRAY) {
				JOptionPane.showMessageDialog(frame, "Please put the letter in a different spot");
			} else {
				prevText.add(field[xPos][yPos].getText());
				prevColor.add(field[xPos][yPos].getBackground());
				// System.out.println(prevText + " " + prevColor.toString());

				field[xPos][yPos].setText(letter);
				field[xPos][yPos].setBackground(Color.LIGHT_GRAY);
				// System.out.println(prevText + " " + prevColor.toString());
				gb.setSquareLetter(xPos, yPos, Letter.clone(currentPlayer.tray[place]));
				// System.out.println(gb.getSquareLetter(xPos + 1,
				// yPos).getLetter());
				trayField[place].setEnabled(false);

				int bonusLetter = gb.getBonusLetter(xPos, yPos);
				int bonusWord = gb.getBonusWord(xPos, yPos);
				int letterValue = gb.getSquareLetter(xPos, yPos).getLetterValue();
				letterValue *= bonusLetter;
				WordLetter wl = new WordLetter(letter.substring(0, 1), xPos, yPos, letterValue, bonusWord);
				word.add(wl);
				// System.out.println(word.toString());
				// System.out.println(letter.substring(0, 1));
				letter = "";
			}
		}
	}

	private class BtnPlayWordActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			hold = new WordLetter[word.size()];
			for (int i = 0; i < hold.length; i++) {
				hold[i] = word.get(i);
			}
			// System.out.println(word.toString());
			WordFormation wf = new WordFormation();
			ArrayList<String> temp = new ArrayList<String>();
			temp = wf.possibleWords(previous, gb);
			boolean check = true;
			for (int i = 0; i < temp.size(); i++) {
				try {
					if (!wf.checkWord(temp.get(i)))
						check = false;
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "There was an error");
					System.exit(-1);
				}
			}
			// do checks
			if (word.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please play a word");
			} else if (turn == 0 && field[7][7].getText().equals("2W")) {
				// System.out.println("got there");
				JOptionPane.showMessageDialog(frame,
						"First word of the game should have a letter on the middle square.");
				btnUndo.doClick();
			} else if (wf.checkAlone(previous, gb)) {
				JOptionPane.showMessageDialog(frame,
						"The word should use at least one letter previously on the board.");
				btnUndo.doClick();
			} else if (!check) {
				JOptionPane.showMessageDialog(frame, "The word does not exist. Please try again!");
				btnUndo.doClick();
			} else {
				calculateScore(wf.possibleWords(previous, gb));
				// System.out.println("score updated");
			}
		}

		private void calculateScore(ArrayList<String> checkedWords) {
			int score = 0;
			int totalScore = 0;
			int loops = 0;
			int wordBonus = 1;
			skips = 0;
			while (loops < checkedWords.size()) {
				if (playerOnesTurn == true)
					score = player1score;
				else
					score = player2score;
				for (int i = 0; i < word.size(); i++) {
					score += word.get(i).getLetterVal();
					if (word.get(i).getWordVal() > 1)
						wordBonus *= word.get(i).getWordVal();
				}
				score *= wordBonus;
				totalScore = totalScore + score;
				loops++;
			}
			if (playerOnesTurn == true) {
				updatePrevious();
				totalScore += Integer.parseInt(txtPlayer1Score.getText());
				txtPlayer1Score.setText(Integer.toString(totalScore));
			} else {
				updatePrevious();
				totalScore += Integer.parseInt(txtPlayer2Score.getText());
				txtPlayer2Score.setText(Integer.toString(totalScore));
			}
			pouch.drawLetters(currentPlayer.tray);
			swapTurn();
		}

	}

	private class BtnSkipTurnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnUndo.doClick();
			if (skips > 3) {
				mntmEndGame.doClick();
			}
			swapTurn();
		}
	}

	private class ButtonUndoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// return all letters user put on board in this turn to the tray

			// System.out.println(word.size() + " " + word.toString());
			try {
				for (int i = 0; i < hold.length; i++) {
					int x = hold[i].getX();
					int y = hold[i].getY();
					// System.out.println(hold[i].toString());
					// System.out.println(x + ", " + y + ": " + prevText.get(i)
					// + " " + prevColor.toString());
					field[x][y].setText(prevText.get(i));
					field[x][y].setBackground(prevColor.get(i));
					gb.setSquareLetter(x, y, null);
				}
			} catch (NullPointerException e) {
				for (int i = 0; i < word.size(); i++) {
					int x = word.get(i).getX();
					int y = word.get(i).getY();
					// System.out.println(word.toString());
					// System.out.println(x + ", " + y + ": " + prevText.get(i)
					// + " " + prevColor.toString());
					field[x][y].setText(prevText.get(i));
					field[x][y].setBackground(prevColor.get(i));
					gb.setSquareLetter(x, y, null);
				}
			}
			prevText.clear();
			prevColor.clear();
			word.clear();
			hold = null;
			letter = "";

			for (int i = 0; i < trayField.length; i++) {
				trayField[i].setEnabled(true);
			}
		}
	}

	private class MntmAboutScrabbleActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			About aboutWin = new About(version);
			aboutWin.setVisible(true);
		}
	}

	private class MntmHelpActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Help helpWin = new Help();
			helpWin.setVisible(true);
		}
	}

	private class MntmRestartActionListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (evt.getSource() == mntmRestart) {
				dispose();
				try {
					Scrabble window = new Scrabble();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void dispose() {
			frame.dispose();
		}
	}

	private class MntmEndGameActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// subtract current player's remaining letters from their score
			for (int i = 0; i < currentPlayer.tray.length; i++) {
				if ((currentPlayer.tray[i] != null) || (Scrabble.trayField[i].isEnabled() == true)) {
					currentPlayer.score -= currentPlayer.tray[i].getLetterValue();
				}
			}

			if (playerOnesTurn == true) {
				txtPlayer1Score.setText("" + currentPlayer.getScore());
			} else {
				txtPlayer2Score.setText("" + currentPlayer.getScore());
			}

			// swapTurn() without textField display or message
			turn++;
			playerOnesTurn = !playerOnesTurn;
			if (playerOnesTurn == true) {
				currentPlayer = playerOne;
			} else {
				currentPlayer = playerTwo;
			}

			// subtract other player's remaining letters from their score
			for (int i = 0; i < currentPlayer.tray.length; i++) {
				if ((currentPlayer.tray[i] != null) || (Scrabble.trayField[i].isEnabled() == true)) {
					currentPlayer.score -= currentPlayer.tray[i].getLetterValue();
				}
			}

			if (playerOnesTurn == true) {
				txtPlayer1Score.setText("" + currentPlayer.getScore());
			} else {
				txtPlayer2Score.setText("" + currentPlayer.getScore());
			}

			// disable the controls
			for (int i = 0; i < trayField.length; i++) {
				trayField[i].setEnabled(false);
			}
			btnPlayWord.setEnabled(false);
			btnSkipTurn.setEnabled(false);
			btnUndo.setEnabled(false);

			// show the winner
			player1score = Integer.parseInt(txtPlayer1Score.getText());
			player2score = Integer.parseInt(txtPlayer2Score.getText());
			String pathToEndScreenOnDisk = "C:\\Users\\Uday Mahajan\\Documents\\ECLIPSE\\Scrabble\\Images\\endScreen.jpg";
			ImageIcon Image2 = new ImageIcon(pathToEndScreenOnDisk);
			
			
			if (player1score > player2score) {
				JOptionPane.showMessageDialog(frame, "PLAYER 1 IS THE WINNER!!!!!");
			} else if (player2score > player1score) {
				JOptionPane.showMessageDialog(frame, "PLAYER 2 IS THE WINNEr!!!!!");
			} else
				JOptionPane.showMessageDialog(frame, "THE GAME IS A TIE!!!!!");
			
			JOptionPane.showMessageDialog(null, new JLabel("", Image2, JLabel.CENTER), "Congratulations",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static JButton[] getTrayField() {
		return trayField;
	}
}
