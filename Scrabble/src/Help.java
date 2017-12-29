import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Help extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Help dialog = new Help();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Help() {
		setBounds(100, 100, 515, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblScrabbleRules = new JLabel("How to play Scrabble:");
			lblScrabbleRules.setBounds(10, 11, 127, 14);
			contentPanel.add(lblScrabbleRules);
		}
		{
			JLabel lblMainGoalForm = new JLabel("Main Goal: Form words using the seven random letters given to you and letters ");
			lblMainGoalForm.setBounds(10, 36, 464, 14);
			contentPanel.add(lblMainGoalForm);
		}
		{
			JLabel lblAlreadyPlacedOn = new JLabel("already placed on the board.");
			lblAlreadyPlacedOn.setBounds(10, 61, 464, 14);
			contentPanel.add(lblAlreadyPlacedOn);
		}
		{
			JLabel lblHowToWin = new JLabel("How to Win: Earn the more points than your oponent before all the tiles are used.");
			lblHowToWin.setBounds(10, 86, 464, 14);
			contentPanel.add(lblHowToWin);
		}
		{
			JLabel lblRulesTheFirst = new JLabel("Rules: The first word played must have one letter touching the center. All the other");
			lblRulesTheFirst.setBounds(10, 111, 464, 14);
			contentPanel.add(lblRulesTheFirst);
		}
		{
			JLabel lblWordsMustUse = new JLabel("words must use at least one letter that was previously present on the board.");
			lblWordsMustUse.setBounds(10, 136, 464, 14);
			contentPanel.add(lblWordsMustUse);
		}
		{
			JLabel lblFeaturesIfNeither = new JLabel("Features: If a player cannot play any words, they can click the Skip Turn button.");
			lblFeaturesIfNeither.setBounds(10, 161, 464, 14);
			contentPanel.add(lblFeaturesIfNeither);
		}
		{
			JLabel lblToReturnAny = new JLabel("To return any letters played during the current turn click the Undo button.");
			lblToReturnAny.setBounds(10, 186, 464, 14);
			contentPanel.add(lblToReturnAny);
		}
		{
			JLabel lblIfNeitherPlayer = new JLabel("If neither player can make another move, click the End Game button in the");
			lblIfNeitherPlayer.setBounds(10, 236, 464, 14);
			contentPanel.add(lblIfNeitherPlayer);
		}
		{
			JLabel lblWhoWon = new JLabel("Actions Menu to find out who won!");
			lblWhoWon.setBounds(10, 261, 464, 14);
			contentPanel.add(lblWhoWon);
		}
		{
			JLabel lblToRestartThe = new JLabel("To restart the game from the beginning click the Restart button in the Actions Menu.");
			lblToRestartThe.setBounds(10, 211, 479, 14);
			contentPanel.add(lblToRestartThe);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OkButtonActionListener());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	private class OkButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
}
