package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Connect;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class gaster_list extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gaster_list frame = new gaster_list();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JButton btnSkrivUtGster;
	private JLabel lblPersonnr;
	private JLabel lblMobnr;
	private JLabel lblLgenhetsnummer;
	private JLabel lblEmail;

	/**
	 * Create the frame.
	 */
	
	public gaster_list() {

		Connection conn = Connect.getConnection();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 1100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setBounds(100, 100, 1100, 800);
		contentPane.add(table);

		btnSkrivUtGster = new JButton("Skriv ut gäster");
		btnSkrivUtGster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)

			{
				String query = "select * from hyresgaster";
				try {
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSkrivUtGster.setBounds(560, 30, 117, 29);
		contentPane.add(btnSkrivUtGster);

		JLabel lblNamn = new JLabel("Namn");
		lblNamn.setBounds(100, 72, 61, 16);
		contentPane.add(lblNamn);

		JLabel lblEfternamn = new JLabel("Efternamn");
		lblEfternamn.setBounds(284, 72, 89, 16);
		contentPane.add(lblEfternamn);

		lblPersonnr = new JLabel("Person_nr");
		lblPersonnr.setBounds(468, 72, 77, 16);
		contentPane.add(lblPersonnr);

		lblMobnr = new JLabel("Mob_nr");
		lblMobnr.setBounds(650, 72, 61, 16);
		contentPane.add(lblMobnr);

		lblLgenhetsnummer = new JLabel("L\u00E4genhetsnummer");
		lblLgenhetsnummer.setBounds(1018, 72, 182, 16);
		contentPane.add(lblLgenhetsnummer);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(834, 72, 61, 16);
		contentPane.add(lblEmail);
	}
}
