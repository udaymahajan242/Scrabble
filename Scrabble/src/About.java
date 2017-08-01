import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class About extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JLabel lblVersion;
	JLabel lblWrittenBy;
	JLabel lblElanaHummel;
	JLabel lblEthanCheatham;
	JLabel lblTonyLauricella;
	JLabel lblUdayMahajan;

	/**
	 * Create the dialog.
	 */
	public About(String version) {
		setTitle("About Deployment");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblVersion = new JLabel("Version " + version);
		lblVersion.setBounds(10, 36, 108, 14);
		contentPanel.add(lblVersion);
		
		lblWrittenBy = new JLabel("Writers:");
		lblWrittenBy.setBounds(10, 95, 94, 14);
		contentPanel.add(lblWrittenBy);
		
		lblElanaHummel = new JLabel("Elana Hummel");
		lblElanaHummel.setBounds(10, 120, 94, 14);
		contentPanel.add(lblElanaHummel);
		
		lblEthanCheatham = new JLabel("Ethan Cheatham");
		lblEthanCheatham.setBounds(10, 142, 94, 14);
		contentPanel.add(lblEthanCheatham);
		
		lblTonyLauricella = new JLabel("Tony Lauricella");
		lblTonyLauricella.setBounds(10, 167, 94, 14);
		contentPanel.add(lblTonyLauricella);
		
		lblUdayMahajan = new JLabel("Uday Mahajan");
		lblUdayMahajan.setBounds(10, 192, 94, 14);
		contentPanel.add(lblUdayMahajan);
		
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

