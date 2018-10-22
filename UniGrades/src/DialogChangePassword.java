import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogChangePassword extends JDialog {
	private JPasswordField tfStariPassword;
	private JPasswordField tfNoviPassword;
	private JPasswordField tfNoviPasswordPonovo;
	public DialogChangePassword()
	{
		JPanel jpMain = new JPanel();
		getContentPane().add(jpMain, BorderLayout.CENTER);
		jpMain.setLayout(null);
		
		JLabel lblStariPassword = new JLabel("Stari password:");
		lblStariPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStariPassword.setBounds(10, 11, 151, 14);
		jpMain.add(lblStariPassword);
		
		tfStariPassword = new JPasswordField();
		tfStariPassword.setBounds(171, 8, 98, 20);
		jpMain.add(tfStariPassword);
		
		JLabel lblNoviPassword = new JLabel("Novi password:");
		lblNoviPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNoviPassword.setBounds(10, 36, 151, 14);
		jpMain.add(lblNoviPassword);
		
		tfNoviPassword = new JPasswordField();
		tfNoviPassword.setBounds(171, 33, 98, 20);
		jpMain.add(tfNoviPassword);
		
		JLabel lblNoviPasswordponovo = new JLabel("Novi password (ponovo):");
		lblNoviPasswordponovo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNoviPasswordponovo.setBounds(10, 61, 151, 14);
		jpMain.add(lblNoviPasswordponovo);
		
		tfNoviPasswordPonovo = new JPasswordField();
		tfNoviPasswordPonovo.setBounds(171, 58, 98, 20);
		jpMain.add(tfNoviPasswordPonovo);
		
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfStariPassword.getText().isEmpty()||tfNoviPassword.getText().isEmpty()||tfNoviPasswordPonovo.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(DialogChangePassword.this, "Morate popuniti sva polja!","Greska",JOptionPane.ERROR_MESSAGE);
					tfStariPassword.setText("");
					tfNoviPassword.setText("");
					tfNoviPasswordPonovo.setText("");
				}
				else if(!tfStariPassword.getText().equals(Login.password))
				{
					JOptionPane.showMessageDialog(DialogChangePassword.this, "Netacan password!","Greska",JOptionPane.ERROR_MESSAGE);
					tfStariPassword.setText("");
					tfNoviPassword.setText("");
					tfNoviPasswordPonovo.setText("");
				}
				else if(!tfNoviPassword.getText().equals(tfNoviPasswordPonovo.getText()))
				{
					JOptionPane.showMessageDialog(DialogChangePassword.this, "Password u poljima 'Novi password' i 'Novi password (ponovo)' se ne poklapa","Greska",JOptionPane.ERROR_MESSAGE);
					tfStariPassword.setText("");
					tfNoviPassword.setText("");
					tfNoviPasswordPonovo.setText("");
				}
				else
				{
					Login.password=tfNoviPasswordPonovo.getText();
					JOptionPane.showMessageDialog(DialogChangePassword.this, "Password uspesno promenjen!","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				
			}
		});
		btnPotvrdi.setBounds(180, 89, 89, 23);
		jpMain.add(btnPotvrdi);
		setTitle("Promena password-a");
		setSize(315, 165);
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
