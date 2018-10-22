import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class DialogChangeUsername extends JDialog {
	private JTextField tfNoviUsername;
	private JPasswordField tfPassword;
	public DialogChangeUsername() {
		JPanel jpMain = new JPanel();
		getContentPane().add(jpMain, BorderLayout.CENTER);
		jpMain.setLayout(null);
		
		JLabel lblNoviUsername = new JLabel("Novi username:");
		lblNoviUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNoviUsername.setBounds(10, 11, 119, 14);
		jpMain.add(lblNoviUsername);
		
		tfNoviUsername = new JTextField();
		tfNoviUsername.setBounds(139, 8, 86, 20);
		jpMain.add(tfNoviUsername);
		tfNoviUsername.setColumns(10);
		
		JLabel lblOpis = new JLabel("");
		lblOpis.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpis.setBounds(10, 104, 232, 14);
		jpMain.add(lblOpis);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 42, 119, 14);
		jpMain.add(lblPassword);
		
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfNoviUsername.getText().isEmpty()||tfPassword.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(DialogChangeUsername.this, "Morate popuniti polja!","Greska!",JOptionPane.ERROR_MESSAGE);
					
				}
				else if(!tfNoviUsername.getText().isEmpty()&&tfPassword.getText().equals(Login.password))
				{
					Login.userName=tfNoviUsername.getText();
					JOptionPane.showMessageDialog(DialogChangeUsername.this, "Username uspešno promenjen.","Greska!",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else if(!tfNoviUsername.getText().isEmpty()&&!tfPassword.getText().equals(Login.password))
				{
					lblOpis.setForeground(Color.RED);
					tfPassword.setText("");
					lblOpis.setText("Neispravna lozinka.");
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						  @Override
						  public void run() {
						    lblOpis.setText("");
						  }
						}, 2500);
				}
			}
		});
		btnPotvrdi.setBounds(139, 70, 89, 23);
		jpMain.add(btnPotvrdi);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(139, 39, 86, 20);
		jpMain.add(tfPassword);
		
		setSize(266,167);
		setModal(true);
		setTitle("Promena username-a");
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
