package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import backend.EmptyApartment;
import backend.Hyresgast;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

@SuppressWarnings("unchecked")
public class EnterData extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainframe;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField personNumber;
	private JTextField phoneNumber;
	private JTextField email;
	private JTextField apartmentNumber;
	private JLabel lblPersonnummer;
	private JLabel lblMobilnummer;
	private JLabel lblEmail;
	private JLabel lblLagenhetsnummer;
	private JButton btnSkrivUtGster;
	private JTable table;
	private JTable table_1;
	private JScrollPane scrollPane;
	private String _firstName="förnamn";
	private String _lastName="efternamn";
	private String _personNumber="personnummr";
	private String _phoneNumber="telefonnummer";
	private String _email="email";
	private String _apartmentNumber="lägenhetsnummer";
	private String id="id";
	private String address="Address";
	private String postal_code="postnummer";
	private String city="Stad";
	private String size="storlek";
	private String rooms="rum";
	private String rent="hyra";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterData frame = new EnterData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public EnterData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 800);
		mainframe = new JPanel();
		mainframe.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainframe);
		mainframe.setLayout(null);

		JLabel lblRegistreraNyHyresgast = new JLabel("Registrera Ny Hyresgast");
		lblRegistreraNyHyresgast.setBounds(45, 46, 174, 16);
		mainframe.add(lblRegistreraNyHyresgast);

		firstName = new JTextField();
		firstName.setBounds(176, 100, 160, 26);
		mainframe.add(firstName);
		firstName.setColumns(10);

		lastName = new JTextField();
		lastName.setBounds(176, 150, 160, 26);
		mainframe.add(lastName);
		lastName.setColumns(10);

		personNumber = new JTextField();
		personNumber.setBounds(176, 200, 160, 26);
		mainframe.add(personNumber);
		personNumber.setColumns(10);

		phoneNumber = new JTextField();
		phoneNumber.setBounds(176, 250, 160, 26);
		mainframe.add(phoneNumber);
		phoneNumber.setColumns(10);

		email = new JTextField();
		email.setBounds(176, 300, 160, 26);
		mainframe.add(email);
		email.setColumns(10);

		apartmentNumber = new JTextField();
		apartmentNumber.setBounds(176, 350, 160, 26);
		mainframe.add(apartmentNumber);
		apartmentNumber.setColumns(10);

		JLabel lblNamn = new JLabel("Namn");
		lblNamn.setBounds(45, 104, 61, 16);
		mainframe.add(lblNamn);

		JLabel lblEfternamn = new JLabel("Efternamn");
		lblEfternamn.setBounds(45, 154, 88, 16);
		mainframe.add(lblEfternamn);

		lblPersonnummer = new JLabel("Personnummer");
		lblPersonnummer.setBounds(45, 204, 99, 16);
		mainframe.add(lblPersonnummer);

		lblMobilnummer = new JLabel("Mobilnummer");
		lblMobilnummer.setBounds(45, 254, 99, 16);
		mainframe.add(lblMobilnummer);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(45, 304, 61, 16);
		mainframe.add(lblEmail);

		lblLagenhetsnummer = new JLabel("Lagenhetsnummer");
		lblLagenhetsnummer.setBounds(45, 354, 130, 16);
		mainframe.add(lblLagenhetsnummer);

		DefaultTableModel model = new DefaultTableModel();

		// Create a couple of columns
		model.addColumn("Col1");
		model.addColumn("Col2");

		// Append a row
		model.addRow(new Object[] { "v1", "v2" });

		JButton btnSpara = new JButton("Spara");
		btnSpara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Socket socket = new Socket("localhost", 2277);
						OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
						PrintWriter writer = new PrintWriter(out, true);
						InputStreamReader input = new InputStreamReader(socket.getInputStream());
						BufferedReader reader = new BufferedReader(input);) {
					writer.println("spara");
					writer.println(firstName.getText());
					writer.println(lastName.getText());
					writer.println(personNumber.getText());
					writer.println(phoneNumber.getText());
					writer.println(email.getText());
					writer.println(apartmentNumber.getText());
					int response = reader.read();
					if (response==172) {
						JOptionPane.showMessageDialog(null, "Gästen är registrerad");
						firstName.setText("");
						lastName.setText("");
						personNumber.setText("");
						phoneNumber.setText("");
						email.setText("");
						apartmentNumber.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Något gick fel");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		btnSpara.setBounds(176, 411, 130, 29);
		mainframe.add(btnSpara);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(371, 106, 838, 515);
		mainframe.add(scrollPane);

		table_1 = new JTable();
		;
		table_1.setBounds(371, 106, 838, 515);
		mainframe.add(table_1);
		
		scrollPane.setViewportView(table_1);

		JButton btnSkrivUtHyresgster = new JButton("Skriv ut hyresgÃ¤ster");

		btnSkrivUtHyresgster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Socket socket = new Socket("localhost", 2277);
						OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
						PrintWriter writer = new PrintWriter(out, true);) {

					writer.println("data");
					ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
					List<Hyresgast> hyresgaster = (List<Hyresgast>) inputStream.readObject();
					DefaultTableModel model = new DefaultTableModel(new Object[] { _firstName, _lastName, _personNumber,
							_phoneNumber, _email, _apartmentNumber }, 0);
					for (Hyresgast gast : hyresgaster) {
						model.addRow(new Object[] { gast.getFirstName(), gast.getLastName(), gast.getPersonNumber(),
								gast.getPhone_number(), gast.getEmail(), gast.getApartmentNumber() });

					}
					table_1.setModel(model);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		btnSkrivUtHyresgster.setBounds(550, 60, 184, 29);
		mainframe.add(btnSkrivUtHyresgster);

		JButton printApartments = new JButton("Skriv ut lÃ¤genheter");
		printApartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Socket socket = new Socket("localhost", 2277);
						OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
						PrintWriter writer = new PrintWriter(out, true);) {
					writer.println("emptyApartments");
					ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
					List<EmptyApartment> emptyApartments = (List<EmptyApartment>) inputStream.readObject();
					DefaultTableModel model = new DefaultTableModel(
							new Object[] { id, address, postal_code, city, size, rooms, rent },0);
					for (EmptyApartment empty : emptyApartments) {
						model.addRow(new Object[] { empty.getId(), empty.getAddress(), empty.getPostal_code(),
								empty.getCity(), empty.getSize(), empty.getRooms(), empty.getRent() });
					}
					table_1.setModel(model);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		printApartments.setBounds(800, 60, 184, 29);
		mainframe.add(printApartments);
		rootPane.setDefaultButton(btnSpara);

	}
}
