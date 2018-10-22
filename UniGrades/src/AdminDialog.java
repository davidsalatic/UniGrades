import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminDialog extends JDialog {
	private JTextField tfMaxPredmeta;
	private JTextField tfMaxObaveza;
	public AdminDialog(JTable jtTabela) {
		JLabel lblMaxPredmeta = new JLabel("Maksimalno predmeta :");
		lblMaxPredmeta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaxPredmeta.setBounds(10, 11, 157, 14);
		getContentPane().add(lblMaxPredmeta);
		
		
		tfMaxPredmeta = new JTextField();
		tfMaxPredmeta.setBounds(177, 8, 28, 20);
		getContentPane().add(tfMaxPredmeta);
		tfMaxPredmeta.setColumns(10);
		
		JLabel lblUpozorenje = new JLabel("");
		lblUpozorenje.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpozorenje.setBounds(10, 155, 324, 14);
		getContentPane().add(lblUpozorenje);
		
		JButton btnRefreshMaxPredmeta = new JButton("",new ImageIcon(MainWindow.class.getResource("/save.png")));
		btnRefreshMaxPredmeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int n=Integer.parseInt(tfMaxPredmeta.getText());
					MainWindow.maxPredmeta=n-1;
					System.out.println(""+MainWindow.maxPredmeta+1);
					lblUpozorenje.setText("Maksimalan broj predmeta promenjen na: "+(MainWindow.maxPredmeta+1));
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						  @Override
						  public void run() {
						    lblUpozorenje.setText("");
						  }
						}, 2500);
				} catch (NumberFormatException e2) {
					lblUpozorenje.setText("GREŠKA! Moraš uneti brojeve u tekst polje!");
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						  @Override
						  public void run() {
						    lblUpozorenje.setText("");
						  }
						}, 2500);
				}
			}
		});
		btnRefreshMaxPredmeta.setBounds(215, 8, 19, 19);
		getContentPane().add(btnRefreshMaxPredmeta);
		
		JLabel lblMaxObaveza = new JLabel("Maksimalno obaveza:");
		lblMaxObaveza.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaxObaveza.setBounds(10, 37, 157, 14);
		getContentPane().add(lblMaxObaveza);
		
		tfMaxObaveza = new JTextField();
		tfMaxObaveza.setBounds(177, 34, 28, 20);
		getContentPane().add(tfMaxObaveza);
		tfMaxObaveza.setColumns(10);
		
		JButton btnRefreshMaxObaveza = new JButton("",new ImageIcon(MainWindow.class.getResource("/save.png")));
		btnRefreshMaxObaveza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int n=Integer.parseInt(tfMaxObaveza.getText());
					MainWindow.maxObaveza=n-1;
					lblUpozorenje.setText("Maksimalan broj obaveza promenjen na: "+(MainWindow.maxObaveza+1));
				} catch (NumberFormatException e2) {
					lblUpozorenje.setText("GREŠKA! Moraš uneti brojeve u tekst polje!");
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						  @Override
						  public void run() {
						    lblUpozorenje.setText("");
						  }
						}, 2500);
				}
			}
		});
		btnRefreshMaxObaveza.setBounds(215, 36, 19, 18);
		getContentPane().add(btnRefreshMaxObaveza);
		
		JLabel lblBodovniSistem = new JLabel("Bodovni sistem:");
		lblBodovniSistem.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBodovniSistem.setBounds(10, 62, 157, 14);
		getContentPane().add(lblBodovniSistem);
		
		JComboBox<String> cbBodovniSistem = new JComboBox<String>();
		cbBodovniSistem.setBounds(177, 59, 102, 20);
		cbBodovniSistem.addItem("Posle 2018. (51%)");
		cbBodovniSistem.addItem("Pre 2018. (55%)");
		getContentPane().add(cbBodovniSistem);
		
		JButton btnRefreshBodovniSistem = new JButton("", new ImageIcon(MainWindow.class.getResource("/save.png")));
		btnRefreshBodovniSistem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbBodovniSistem.getSelectedIndex()==1)
				{
					lblUpozorenje.setText("Ocene se računaju po starom bodovnom sistemu! (55%)");
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						  @Override
						  public void run() {
						    lblUpozorenje.setText("");
						  }
						}, 2500);
					MainWindow.bodovniSistem=55;
				}
				else if(cbBodovniSistem.getSelectedIndex()==0)
				{
					lblUpozorenje.setText("Ocene se računaju po novom bodovnom sistemu! (51%)");
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						  @Override
						  public void run() {
						    lblUpozorenje.setText("");
						  }
						}, 2500);
					MainWindow.bodovniSistem=51;
				}
			}
		});
		btnRefreshBodovniSistem.setBounds(288, 61, 19, 18);
		getContentPane().add(btnRefreshBodovniSistem);
		
		setModal(true);
		setTitle("Administrator podešavanja");
		setSize(369, 227);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JButton btnPromenaUsernamea = new JButton("Promena username-a");
		btnPromenaUsernamea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogChangeUsername dialogChangeUsername=new DialogChangeUsername();
			}
		});
		btnPromenaUsernamea.setBounds(89, 87, 190, 23);
		getContentPane().add(btnPromenaUsernamea);
		
		JButton btnNewButton = new JButton("Promena password-a");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogChangePassword dialogChangePassword = new DialogChangePassword();
			}
		});
		btnNewButton.setBounds(89, 121, 190, 23);
		getContentPane().add(btnNewButton);
		setVisible(true);
	}
}
