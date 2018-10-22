import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JDialog{
	private JTextField tfUserName;
	private JPasswordField tfPassword;
	public static String userName="admin";
	public static String password="admin";
	public String getUserName() {
		return userName;
	}
	
	public Login(JTable jtTabela) {
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setBounds(10, 11, 97, 14);
		getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 36, 97, 14);
		getContentPane().add(lblPassword);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(117, 8, 86, 20);
		getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfUserName.getText().equals(userName)&&tfPassword.getText().equals(password))
				{
					dispose();
					AdminDialog adm = new AdminDialog(jtTabela);
				}
				else
				{
					JOptionPane.showMessageDialog(Login.this, "Neispravno korisničko ime/lozinka ili lozinka!","Neuspešno prijavljivanje",JOptionPane.ERROR_MESSAGE);
					tfPassword.setText("");
				}
			}
		});
		btnPotvrdi.setBounds(117, 74, 89, 23);
		getContentPane().add(btnPotvrdi);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(117, 33, 86, 20);
		getContentPane().add(tfPassword);
		
		setModal(true);
		setTitle("Prijavljivanje");
		setSize(227, 137);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
