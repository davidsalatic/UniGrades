import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.IllegalComponentStateException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class DialogDodavanje extends JDialog {
	private JTextField tfESPB;
	private JTextField tfImePredmeta;
	private JTextField tfUnos;
	public int brojStavki=0;
	public static int trenutniRed=0;//za postavljanje Ime predmeta,ESPB,Ukupno i Ocena u svaki red
	private JLabel lblOpis;
	private JLabel lblImePredmeta;
	private JLabel lblEspb;
	private JLabel lblCifra;
	private JLabel lblStavke;
	private JList<String> jlStavke;
	private JLabel lblbrojStavki;
	
	public DialogDodavanje(DefaultTableModel dtm,JTable jtTabela, DefaultListModel<String>stavke) {
		stavke.clear();//brisanje stavki kako se ne bi pojavile prethodne kad se dodaje novi predmet
		
		JPanel jpMain = new JPanel();
		getContentPane().add(jpMain, BorderLayout.CENTER);
		jpMain.setLayout(null);
		
		lblOpis= new JLabel("");
		lblOpis.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblOpis.setBounds(10, 300, 164, 14);
		jpMain.add(lblOpis);
	
		lblImePredmeta = new JLabel("Ime predmeta:");
		lblImePredmeta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblImePredmeta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImePredmeta.setBounds(56, 11, 118, 29);
		jpMain.add(lblImePredmeta);
		
		lblEspb = new JLabel("ESPB:");
		lblEspb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEspb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspb.setBounds(95, 51, 79, 29);
		jpMain.add(lblEspb);
		
		tfImePredmeta = new JTextField();
		tfImePredmeta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tfImePredmeta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOpis.setText("|Unesi ime predmeta|");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpis.setText("");
			}
		});
		tfImePredmeta.setBounds(184, 11, 140, 29);
		jpMain.add(tfImePredmeta);
		tfImePredmeta.setColumns(10);
		
		tfESPB = new JTextField();
		tfESPB.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tfESPB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOpis.setText("|Unesi broj ESPB bodova|");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpis.setText("");
			}
		});
		tfESPB.setBounds(184, 51, 140, 29);
		jpMain.add(tfESPB);
		tfESPB.setColumns(10);
		
		lblCifra = new JLabel("");
		lblCifra.setForeground(new Color(51, 0, 204));
		lblCifra.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCifra.setHorizontalAlignment(SwingConstants.LEFT);
		lblCifra.setText(Integer.toString(brojStavki));
		lblCifra.setBounds(163, 244, 19, 14);
		jpMain.add(lblCifra);
		
		lblStavke = new JLabel("OBAVEZE:");
		lblStavke.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblStavke.setHorizontalAlignment(SwingConstants.CENTER);
		lblStavke.setBounds(10, 103, 96, 14);
		jpMain.add(lblStavke);
		
		tfUnos = new JTextField();
		tfUnos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tfUnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOpis.setText("|Unesi obaveze predmeta|");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpis.setText("");
			}
		});
		tfUnos.setBounds(20, 128, 86, 20);
		jpMain.add(tfUnos);
		tfUnos.setColumns(10);
		
		JButton btnUnesi = new JButton("",new ImageIcon(MainWindow.class.getResource("/add.png")));
		btnUnesi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOpis.setText("|Dodaj|");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpis.setText("");
			}
		});
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfUnos.getText().isEmpty())
				{
					//ako ne unese ime obaveze a klikne na dodavanje
				}
				else
				{
					if(brojStavki>MainWindow.maxObaveza)
					{
						JOptionPane.showMessageDialog(DialogDodavanje.this, "Ne možeš uneti više od "+(MainWindow.maxObaveza+1)+" obaveza! (Moguća izmena u Admin podešavanju)","Greška!",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
					stavke.addElement(tfUnos.getText());//dodavanje stavke u jListu stavke
					brojStavki++;
					tfUnos.setText("");
					}
				}
			
				lblCifra.setText(Integer.toString(brojStavki)); 
			}
		});
		btnUnesi.setBounds(17, 159, 36, 23);
		jpMain.add(btnUnesi);
		
		JScrollPane spStavke = new JScrollPane();
		spStavke.setBounds(116, 115, 208, 130);
		jpMain.add(spStavke);
		
		jlStavke = new JList<String>();
		jlStavke.setForeground(new Color(0, 0, 204));
		jlStavke.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		jlStavke.setModel(stavke);
		spStavke.setViewportView(jlStavke);
		
		JLabel lblSlika = new JLabel();
		ImageIcon icon = new ImageIcon(MainWindow.class.getResource("/book.png")); 
		lblSlika.setIcon(icon);
		lblSlika.setBounds(10, 11, 75, 69);
		jpMain.add(lblSlika);
		
		JButton btnObrisi = new JButton("",new ImageIcon(MainWindow.class.getResource("/delete.png")));
		btnObrisi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOpis.setText("|Obriši poslednji|");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpis.setText("");
			}
		});
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stavke.removeElementAt(stavke.getSize()-1);
				brojStavki--;
				lblCifra.setText(Integer.toString(brojStavki));
			}
		});
		btnObrisi.setBounds(70, 159, 36, 23);
		jpMain.add(btnObrisi);
		
		lblbrojStavki = new JLabel("Stavki:");
		lblbrojStavki.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblbrojStavki.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbrojStavki.setBounds(110, 244, 43, 14);
		jpMain.add(lblbrojStavki);
		
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
				if(brojStavki<1)
				{
					throw new IllegalAccessError();//sprecavanje dodavanje predmeta ako nije uneta nijedna stavka
				}
				if(tfESPB.getText().isEmpty()||tfImePredmeta.getText().isEmpty())
				{
					throw new IllegalComponentStateException();//ako nije uneo broj espb bodova ili ime predmeta
				}
				int proveraESPB=Integer.parseInt(tfESPB.getText());
				
				dtm.setRowCount(dtm.getRowCount()+2);//povecavanje tabele za 2 reda
				if(2+brojStavki+2>dtm.getColumnCount())
				dtm.setColumnCount(2+brojStavki+2);		
				
				for(int i=0;i<dtm.getRowCount();i++)//postavljanje Ime predmeta,ESPB,Ukupno i Ocena u svaki drugi red u tabeli
				{
					if(i%2==0)
					{
					jtTabela.setValueAt("Ime predmeta", i, 0);
					jtTabela.setValueAt("ESPB", i, 1);
					jtTabela.setValueAt("Ocena",DialogDodavanje.trenutniRed,1+brojStavki+2);
					jtTabela.setValueAt("Ukupno",DialogDodavanje.trenutniRed,1+brojStavki+1);
					}
				}
				
				for(int i=0;i<stavke.size();i++)
				{
					jtTabela.setValueAt(stavke.elementAt(i), DialogDodavanje.trenutniRed, 1+(i+1));//upisivanje stavki u tabelu
				}
				DialogDodavanje.trenutniRed++;//upisan jedan red,prelazak na sledeci
				
				jtTabela.setValueAt(tfImePredmeta.getText(), DialogDodavanje.trenutniRed, 0);//upisivanje imena predmeta ispod celije Ime predmeta
				jtTabela.setValueAt(tfESPB.getText(), DialogDodavanje.trenutniRed, 1);//upisivanje ESPB bodova
				
				
				DialogDodavanje.trenutniRed++;//gotov sledeci red
				MainWindow.ukupnoPredmeta++;
				dispose();
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(DialogDodavanje.this, "Moras uneti cifru u polje ESPB!","Greska",JOptionPane.ERROR_MESSAGE);
				}
				catch(IllegalAccessError error)
				{
					JOptionPane.showMessageDialog(DialogDodavanje.this, "Moras uneti bar jednu stavku!","Greska",JOptionPane.ERROR_MESSAGE);
				}
				catch(IllegalComponentStateException ics)
				{
					JOptionPane.showMessageDialog(DialogDodavanje.this, "Polja 'ESPB' i 'Ime predmeta' moraju biti popunjena!","Greska!",JOptionPane.ERROR_MESSAGE);
				}
				}
		});
		btnPotvrdi.setBounds(235, 291, 89, 23);
		jpMain.add(btnPotvrdi);
		
		if(MainWindow.isObican==false)//ako je korisnik u meniju izabrao fanki font
		{
			dialogFontFunky();
		}
		
		setModal(true);
		setTitle("Dodavanje novog predmeta");
		setSize(340, 354);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	public void dialogFontFunky()
	{
		tfImePredmeta.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		lblImePredmeta.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		tfESPB.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		lblEspb.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		lblStavke.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		tfUnos.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		jlStavke.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		lblbrojStavki.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		lblCifra.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		lblOpis.setFont(new Font("Kristen ITC",Font.PLAIN,11));
	}
}
