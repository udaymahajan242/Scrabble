import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class About extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JLabel lblVersion;
	JLabel lblUdayMahajan;

	/**
	 * Create the dialog.
	 */
	public About(String version) {
		setTitle("About Deployment");
		setBounds(100, 100, 523, 413);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblVersion = new JLabel("Version " + version);
		lblVersion.setBounds(207, 81, 196, 14);
		contentPanel.add(lblVersion);
		
		lblUdayMahajan = new JLabel("© Uday Mahajan");
		lblUdayMahajan.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblUdayMahajan.setBounds(161, 144, 238, 54);
		contentPanel.add(lblUdayMahajan);
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(242, 278, 53, 29);
			contentPanel.add(okButton);
			okButton.addActionListener(new OkButtonActionListener());
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

	private class OkButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
}

