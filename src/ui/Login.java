package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAnvndarnamn = new JLabel("Anv\u00E4ndarnamn");
		lblAnvndarnamn.setBounds(59, 91, 127, 16);
		contentPane.add(lblAnvndarnamn);

		JLabel lblLsenord = new JLabel("L\u00F6senord");
		lblLsenord.setBounds(59, 140, 61, 16);
		contentPane.add(lblLsenord);

		username = new JTextField();
		username.setBounds(176, 86, 130, 26);
		contentPane.add(username);
		username.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(176, 135, 130, 26);
		contentPane.add(passwordField);

		JButton btnLoggaIn = new JButton("Logga in");
		btnLoggaIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//try with resources where all the streams get closed automatically
				try (Socket socket = new Socket("localhost", 2277);
						OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
						PrintWriter writer = new PrintWriter(out, true);

						InputStreamReader input = new InputStreamReader(socket.getInputStream());
						BufferedReader reader = new BufferedReader(input)) {
					writer.println(username.getText());
					writer.println(passwordField.getPassword());

					String state = reader.readLine();
					
					//If the state is 1 it means the user has entered correct credentials
					if (state.equals("1")) {
						Login.this.dispose();
						EnterData enterDataView = new EnterData();
						enterDataView.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "användernamnet och/eller lösenordet är fel ");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Det gick inte att koppla");

				}
			}
		});
		rootPane.setDefaultButton(btnLoggaIn);
		btnLoggaIn.setBounds(176, 194, 130, 29);
		contentPane.add(btnLoggaIn);

	}
}
