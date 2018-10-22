import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame {
	private DefaultTableModel dtm;
	private JTable jtTabela;
	private JLabel lblOpis;
	private DefaultListModel<String> stavke;
	public static int ukupnoPredmeta = 0;
	private JPanel jpDugmici;
	private JMenuBar menuBar;
	private JMenu mnIzgled;
	private JMenu mnBoja;
	private JMenu mnFont;
	private JMenuItem mntmPlava;
	private JMenuItem mntmZelena;
	private JMenuItem mntmCrvena;
	private DialogDodavanje dialogPredmet;
	private JMenuItem mntmObian;
	private JMenuItem mntmFunky;
	public static boolean isObican = true; // da li je izabran font obican ili funky
	private JMenuItem mntmPodesavanjaadmin;
	public static int maxPredmeta = 5;// zapravo 6
	public static int maxObaveza = 6;// zapravo 7 jer JList krece od 0
	public static int bodovniSistem = 51; // ako je 51=novi, ako je stari 55
	public static String path = "D:\\";
	private int row = 0;
	private int col = 0;
	private static String boja = "plava"; // boja programa, bira se u meniju
	private JMenu mnPreset;
	private JMenuItem mntmIvSemestari;

	public MainWindow() throws IOException {
		setForeground(SystemColor.activeCaption);
		UIManager.put("OptionPane.yesButtonText", "Da");
		UIManager.put("OptionPane.noButtonText", "Ne");
		stavke = new DefaultListModel<String>(); //u dialogu za dodavanje, stavke predmeta (aktivnost,kolokvijum itd..)

		Image img = new ImageIcon(MainWindow.class.getResource("/book.png")).getImage();
        this.setIconImage(img);
		
		
		JPanel jpMain = new JPanel();
		getContentPane().add(jpMain, BorderLayout.CENTER);
		jpMain.setLayout(new BorderLayout(0, 0));

		jpDugmici = new JPanel();
		jpDugmici.setForeground(new Color(0, 0, 0));
		jpDugmici.setBackground(new Color(217, 218, 255));
		jpMain.add(jpDugmici, BorderLayout.EAST);
		GridBagLayout gbl_jpDugmici = new GridBagLayout();
		gbl_jpDugmici.columnWidths = new int[] { 0, 0 };
		gbl_jpDugmici.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_jpDugmici.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jpDugmici.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		jpDugmici.setLayout(gbl_jpDugmici);

		JLabel lblUpozorenje = new JLabel("");
		lblUpozorenje.setBackground(new Color(255, 204, 255));
		lblUpozorenje.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpozorenje.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jpMain.add(lblUpozorenje, BorderLayout.NORTH);

		lblOpis = new JLabel("");
		lblOpis.setForeground(SystemColor.desktop);
		lblOpis.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblOpis.setBackground(new Color(217, 218, 255));
		lblOpis.setHorizontalAlignment(SwingConstants.CENTER);
		jpMain.add(lblOpis, BorderLayout.SOUTH);

		
		/*
		 * CUVANJE NA FAJL REDOM: 
		 * ukupnoPredmeta 
		 * isObican 
		 * maxPredmeta 
		 * maxObaveza
		 * bodovniSistem 
		 * path 
		 * trenutnired 
		 * username 
		 * password 
		 * rowcount 
		 * colcount 
		 * boja
		 * tabela
		 */

		dtm = new DefaultTableModel(row, col);

		jtTabela = new JTable();
		jtTabela.setRowHeight(25);
		jtTabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		jtTabela.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jtTabela.setBackground(new Color(131, 180, 236));

		jtTabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		jtTabela.setModel(dtm);

		File f = new File(MainWindow.path + "faksapp!.txt");
		if (f.exists() && !f.isDirectory()) {//ako postoji fajl user-settings .txt u kom su sacuvani podaci
			FileReader fileReader;
			fileReader = new FileReader(path + "faksapp!.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ukupnoPredmeta = Integer.parseInt(bufferedReader.readLine());
			isObican = Boolean.parseBoolean(bufferedReader.readLine());
			if (isObican == false)
				fontFunky();
			maxPredmeta = Integer.parseInt(bufferedReader.readLine());
			maxObaveza = Integer.parseInt(bufferedReader.readLine());
			bodovniSistem = Integer.parseInt(bufferedReader.readLine());
			path = bufferedReader.readLine();
			DialogDodavanje.trenutniRed = Integer.parseInt(bufferedReader.readLine());
			Login.userName = bufferedReader.readLine();
			Login.password = bufferedReader.readLine();
			row = Integer.parseInt(bufferedReader.readLine());
			col = Integer.parseInt(bufferedReader.readLine());
			boja = bufferedReader.readLine();
			if (boja.equals("zelena"))
				colorGreen();
			else if (boja.equals("crvena")) {
				colorRed();
			} else {
				colorBlue();
			}
			dtm = new DefaultTableModel(row, col);
			jtTabela.setModel(dtm);
			for (int i = 0; i < row; i++) {//upisivanje vrednosti u tabelu iz fajla
				String line = bufferedReader.readLine();
				String[] values = line.split(",");
				for (int j = 0; j < col; j++) {
					try {
					if (values[j].equals("null"))//ako ucita null, u tabelu upise samo razmak umesto null
						jtTabela.setValueAt("", i, j);
					else
						jtTabela.setValueAt(values[j], i, j);
					}
					catch(ArrayIndexOutOfBoundsException aio)
					{
						System.out.println("err");
					}
				}
			}
		}
		
		JButton btnSettings = new JButton("", new ImageIcon(MainWindow.class.getResource("/table.png")));
		btnSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (MainWindow.ukupnoPredmeta > maxPredmeta) {
					JOptionPane.showMessageDialog(MainWindow.this,
							"Ne može se uneti više od " + (MainWindow.maxPredmeta + 1)
									+ " predmeta! (Administrator settings)",
							"Obavestenje", JOptionPane.INFORMATION_MESSAGE);
				} else
					dialogPredmet = new DialogDodavanje(dtm, jtTabela, stavke);//dodavanje novog predmeta
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOpis.setText("|Dodavanje novog predmeta|");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpis.setText("");
			}
		});
		GridBagConstraints gbc_btnSettings = new GridBagConstraints();
		gbc_btnSettings.insets = new Insets(0, 0, 5, 0);
		gbc_btnSettings.gridx = 0;
		gbc_btnSettings.gridy = 1;
		jpDugmici.add(btnSettings, gbc_btnSettings);

		JButton btnUcitaj = new JButton("", new ImageIcon(MainWindow.class.getResource("/refresh.png")));
		btnUcitaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblOpis.setText("|Osveži tabelu|");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOpis.setText("");
			}
		});
		btnUcitaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i < jtTabela.getRowCount(); i++) {
						for (int j = 0; j < jtTabela.getColumnCount(); j++) {//prolazak kroz tabelu
							try {
								if (jtTabela.getValueAt(i, j).equals("Ukupno")) {//kada dodje do celije "Ukupno" u celiju ispod nje upisuje ukupno bodova
									double ukupno = 0;
									for (int u = 2; u < j; u++) {//sabira sve stavke predmeta
										try {
											ukupno = ukupno + Double.parseDouble((String) jtTabela.getValueAt(i + 1, u));
										} catch (Exception es) {
											ukupno = ukupno + 0; //ako nije unet broj bodova za neku stavku ili je u slovima unet
										}
									}
									if(ukupno%1==0)//ako ukupan broj bodova nema decimalu nego je okrugao
									{
										jtTabela.setValueAt((int)ukupno, i+1, j);//castujemo u int da prikaze ceo broj
									}
									else//ako je broj sa decimalom
									{
										jtTabela.setValueAt(ukupno, i + 1, j);//upisujemo u tabelu decimalan broj
									}
									
									int ocena;
									ocena=generisiOcenu(ukupno);
									jtTabela.setValueAt(ocena, i+1, j+1);//postavljanje ocene
								}
							} catch (NullPointerException npe) {
								System.out.println("Null pointer greska,ocekivana");//iz nekog razloga kad se refresuje cela tabela dodje do null pointera, nzm zasto i nikad necu skontati
							}
						}
					}

				} catch (IndexOutOfBoundsException ioub) {
					System.out.println("Nema unetih predmeta");
				} //catch (NumberFormatException nfe) {
					//System.out.println("Uneta slova umesto brojeva");
				//}
			}

			private int generisiOcenu(double ukupno) {
				if(bodovniSistem==51)
				{
					if(ukupno<51)
						return 5;
					else if(ukupno<61)
						return 6;
					else if(ukupno<71)
						return 7;
					else if(ukupno<81)
						return 8;
					else if(ukupno<91)
						return 9;
					else
						return 10;
				}
				else
				{
					if(ukupno<55)
						return 5;
					else if(ukupno<65)
						return 6;
					else if(ukupno<75)
						return 7;
					else if(ukupno<85)
						return 8;
					else if(ukupno<95)
						return 9;
					else
						return 10;
				}
			}
		});

		JButton btnObrisi = new JButton("", new ImageIcon(MainWindow.class.getResource("/deletetable.png")));
		btnObrisi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblOpis.setText("|Obriši predmet iz tabele|");
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				lblOpis.setText("");
			}
		});
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MainWindow.ukupnoPredmeta < 1) {//ako klikne na brisanje a nema predmeta
					JOptionPane.showMessageDialog(MainWindow.this, "Nema unetih predmeta!", "Greska!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (jtTabela.getSelectedColumn() < 0) {//ako nije selektovao red za brisanje
						JOptionPane.showMessageDialog(MainWindow.this, "Selektuj predmet za brisanje!", "Greska!",
								JOptionPane.ERROR_MESSAGE);
					} else if (jtTabela.getSelectedRow() % 2 == 1) {//ako je selektovao neparan red,tj onaj u kom se nalaze bodovi i ocena brise se taj red i prethodni
						dtm.removeRow(jtTabela.getSelectedRow() - 1);
						dtm.removeRow(jtTabela.getSelectedRow());
						DialogDodavanje.trenutniRed = DialogDodavanje.trenutniRed - 2;//vazno je da se trenutni red smanji za 2 zbog klase DialogDodavanje gde se preko trenutnog reda upisuju stavke u tabelu
						MainWindow.ukupnoPredmeta--;
					} else if (jtTabela.getSelectedRow() % 2 == 0 || jtTabela.getSelectedRow() == 0) {//ako je selektovao paran red gde pisu imena stavki, brise se taj red i naredni
						dtm.removeRow(jtTabela.getSelectedRow() + 1);
						dtm.removeRow(jtTabela.getSelectedRow());
						DialogDodavanje.trenutniRed = DialogDodavanje.trenutniRed - 2;
						MainWindow.ukupnoPredmeta--;
					} else {
						JOptionPane.showMessageDialog(MainWindow.this, "Selektuj predmet za brisanje!", "Greska!",
								JOptionPane.ERROR_MESSAGE);//verovatno se nece desiti
					}
				}
			}
		});
		GridBagConstraints gbc_btnObrisi = new GridBagConstraints();
		gbc_btnObrisi.insets = new Insets(0, 0, 5, 0);
		gbc_btnObrisi.gridx = 0;
		gbc_btnObrisi.gridy = 2;
		jpDugmici.add(btnObrisi, gbc_btnObrisi);
		GridBagConstraints gbc_btnUcitaj = new GridBagConstraints();
		gbc_btnUcitaj.insets = new Insets(0, 0, 5, 0);
		gbc_btnUcitaj.gridx = 0;
		gbc_btnUcitaj.gridy = 3;
		jpDugmici.add(btnUcitaj, gbc_btnUcitaj);

		JScrollPane spTabela = new JScrollPane();
		spTabela.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		jpMain.add(spTabela, BorderLayout.CENTER);
		spTabela.setViewportView(jtTabela);

		setVisible(true);
		setSize(1000, 373);
		setTitle("Faksapp!");
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setJMenuBar(menuBar);

		mnIzgled = new JMenu("Izgled/podesavanja");
		menuBar.add(mnIzgled);

		mnBoja = new JMenu("Glavna boja");
		mnIzgled.add(mnBoja);

		mntmPlava = new JMenuItem("Plava");
		mntmPlava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorBlue();
				boja = "plava";
			}
		});
		mnBoja.add(mntmPlava);

		mntmZelena = new JMenuItem("Zelena");
		mntmZelena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorGreen();
				boja = "zelena";
			}
		});
		mnBoja.add(mntmZelena);

		mntmCrvena = new JMenuItem("Crvena");
		mntmCrvena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorRed();
				boja = "crvena";
			}
		});
		mnBoja.add(mntmCrvena);

		mnFont = new JMenu("Font");
		mnIzgled.add(mnFont);

		mntmObian = new JMenuItem("Obi\u010Dan");
		mntmObian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fontObican();
			}
		});
		mnFont.add(mntmObian);

		mntmFunky = new JMenuItem("Funky");
		mntmFunky.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fontFunky();
			}
		});
		mnFont.add(mntmFunky);

		mntmPodesavanjaadmin = new JMenuItem("Podesavanja(admin)");
		mntmPodesavanjaadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login log = new Login(jtTabela);
			}
		});
		
		mnPreset = new JMenu("Preset");
		mnIzgled.add(mnPreset);
		
		mntmIvSemestari = new JMenuItem("IV semestar (IT)");//ako izabere preset IV semestar
		mntmIvSemestari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int odluka=JOptionPane.showConfirmDialog(MainWindow.this, "Obrisi trenutnu tabelu i postavi preset IV semestra?","Preset",JOptionPane.YES_NO_OPTION);
				if (odluka==JOptionPane.YES_OPTION)
				{//IV semestar preset
					ukupnoPredmeta=6;
					isObican=true;
					maxPredmeta=5;
					maxObaveza=9;
					bodovniSistem=51;
					DialogDodavanje.trenutniRed=12;
					row=12;
					col=14;
					
					dtm = new DefaultTableModel(row, col);
					jtTabela.setModel(dtm);
					
					int brojKolone=0;
					String prviRed="Ime predmeta,ESPB,Test 1,Test 2,Test 3,Test 4,Prisustvo,Aktivnost,Ispit,Ukupno,Ocena,null,null,null,";
					String [] prviNiz=prviRed.split(",");
					for(int i=0;i<prviNiz.length;i++)
					{
						if(prviNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(prviNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String drugiRed="Osnove testiranja softvera,4,,,,,,,,0,5,null,null,null,";
					String [] drugiNiz=drugiRed.split(",");
					for(int i=0;i<drugiNiz.length;i++)
					{
						if(drugiNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(drugiNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String treciRed="Ime predmeta,ESPB,Test 1 (Sonja),Test 2 (Sonja),Test 3 (Sonja),Test 1 (Miroslav),Test 2 (Miroslav),Ispit,Ukupno,Ocena,null,null,null,null,";
					String [] treciNiz=treciRed.split(",");
					for(int i=0;i<treciNiz.length;i++)
					{
						if(treciNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(treciNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String cetvrtiRed="Arhitektura informacionih sistema i racunarske mreze,5,,,,,,,0,5,null,null,null,null,";
					String [] cetvrtiNiz=cetvrtiRed.split(",");
					for(int i=0;i<cetvrtiNiz.length;i++)
					{
						if(cetvrtiNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(cetvrtiNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String petiRed="Ime predmeta,ESPB,pset0,pset1,pset2,pset3,pset4,pset5,pset6,pset7,pset8,Projekat,Ukupno,Ocena,";
					String [] petiNiz=petiRed.split(",");
					for(int i=0;i<petiNiz.length;i++)
					{
						if(petiNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(petiNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String sestiRed="Algoritmi i strukture podataka,4,,,,,,,,,,,0,5,";
					String [] sestiNiz=sestiRed.split(",");
					for(int i=0;i<sestiNiz.length;i++)
					{
						if(sestiNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(sestiNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String sedmiRed="Ime predmeta,ESPB,Test 1,Test 2,Test 3,Projekat,Ukupno,Ocena,null,null,null,null,null,null,";
					String [] sedmiNiz=sedmiRed.split(",");
					for(int i=0;i<sedmiNiz.length;i++)
					{
						if(sedmiNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(sedmiNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String osmiRed="Veb orijenatisane tehnologije,5,,,,,0,5,null,null,null,null,null,null,";
					String [] osmiNiz=osmiRed.split(",");
					for(int i=0;i<osmiNiz.length;i++)
					{
						if(osmiNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(osmiNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String devetiRed="Ime predmeta,ESPB,Zadatak 1,Zadatak 2,Test 1,Test 2,Usmeni,Ukupno,Ocena,null,null,null,null,null,";
					String [] devetiNiz=devetiRed.split(",");
					for(int i=0;i<devetiNiz.length;i++)
					{
						if(devetiNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(devetiNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String desetiRed="Sistem menadzmenta kvalitetom,6,,,,,,0,5,null,null,null,null,null,";
					String [] desetiNiz=desetiRed.split(",");
					for(int i=0;i<desetiNiz.length;i++)
					{
						if(desetiNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(desetiNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String jedanaestiRed="Ime predmeta,ESPB,UML,Access,Usmeni,Ukupno,Ocena,null,null,null,null,null,null,null,";
					String [] jedanaestiNiz=jedanaestiRed.split(",");
					for(int i=0;i<jedanaestiNiz.length;i++)
					{
						if(jedanaestiNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(jedanaestiNiz[i], brojKolone, i);
					}
					brojKolone++;
					
					String dvanaestiRed="Projektovanje informacionih sistema,6,,,,0,5,null,null,null,null,null,null,null,";
					String [] dvanaestiNiz=dvanaestiRed.split(",");
					for(int i=0;i<dvanaestiNiz.length;i++)
					{
						if(dvanaestiNiz[i].equals("null"))
							jtTabela.setValueAt("", brojKolone, i);
						else
						jtTabela.setValueAt(dvanaestiNiz[i], brojKolone, i);
					}
					brojKolone++;
					
				}
				else
				{
					//ako je kliknuo ne na pitanje da li zeli da postavi preset
				}
			}
		});
		mnPreset.add(mntmIvSemestari);
		mnIzgled.add(mntmPodesavanjaadmin);
		
		
		MainWindow.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		MainWindow.this.addWindowListener(new java.awt.event.WindowAdapter() {
			/*
			 * CUVANJE NA FAJL REDOM: 
			 * ukupnoPredmeta 
			 * isObican 
			 * maxPredmeta 
			 * maxObaveza
			 * bodovniSistem 
			 * path 
			 * trenutnired 
			 * username 
			 * password 
			 * rowcount 
			 * colcount 
			 * boja
			 * tabela
			 */
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				PrintWriter os;
				try {
					ArrayList<String> lista = new ArrayList<String>();
					for(int i=0;i<lista.size();i++)
					{
						System.out.println(lista.get(i));
					}
					os = new PrintWriter(MainWindow.path + "\\" + "faksapp!.txt", "UTF-8");
					os.println(ukupnoPredmeta);
					os.println(isObican);
					os.println(maxPredmeta);
					os.println(maxObaveza);
					os.println(bodovniSistem);
					os.println(path);
					os.println(DialogDodavanje.trenutniRed);
					os.println(Login.userName);
					os.println(Login.password);
					os.println(jtTabela.getRowCount());
					os.println(jtTabela.getColumnCount());
					os.println(boja);
					for (int row = 0; row < jtTabela.getRowCount(); row++) {
						for (int col = 0; col < jtTabela.getColumnCount(); col++) {
							os.print(jtTabela.getValueAt(row, col) + ",");
						}
						os.println();
					}

					os.close();
					System.exit(0);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void colorBlue() {
		jtTabela.setBackground(new Color(131, 180, 236));
		jpDugmici.setBackground(new Color(217, 218, 255));
	}

	public void colorGreen() {
		jtTabela.setBackground(new Color(99, 170, 92));
		jpDugmici.setBackground(new Color(165, 253, 165));
	}

	public void colorRed() {
		jtTabela.setBackground(new Color(248, 46, 43));
		jpDugmici.setBackground(new Color(248, 130, 115));
	}

	public void fontFunky() {
		MainWindow.isObican = false;
		jtTabela.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		lblOpis.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
	}

	public void fontObican() {
		MainWindow.isObican = true;
		jtTabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblOpis.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	}

	public static void main(String[] args) throws IOException {
		MainWindow mejn = new MainWindow();
	}

}
