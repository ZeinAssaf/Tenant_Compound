package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

import database.Connect;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(2277);
			System.out.println("server is running");
			Socket socket;
			int state = Integer.MIN_VALUE;
			Connect connect = new Connect();
			Connection con = Connect.getConnection();
			while (state != 1) {
				socket = server.accept();
				// Write to users
				OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
				PrintWriter writer = new PrintWriter(out, true);

				// Read information from users
				InputStreamReader input = new InputStreamReader(socket.getInputStream());
				BufferedReader reader = new BufferedReader(input);

				// Här Servern tar emot datan från Login klassen
				String username = reader.readLine();
				String password = reader.readLine();
				state = connect.loginAdmin(username, password, con);
				writer.println(Integer.toString(state));
				// Den här kan vi ta bort senare men den är när man testar
				System.out.println(username + " " + password);

			}
			while (true) {
				socket = server.accept();
				// Write to users
				OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
				PrintWriter writer = new PrintWriter(out, true);

				// Read information from users
				InputStreamReader input = new InputStreamReader(socket.getInputStream());
				BufferedReader reader = new BufferedReader(input);

				String firstName = reader.readLine();
				String lastNmae = reader.readLine();
				String personalNumber = reader.readLine();
				String phoneNumber = reader.readLine();
				String emailAddress = reader.readLine();
				String apartmentNumber = reader.readLine();
				System.out.println(firstName + " " + lastNmae + " " + personalNumber + " " + phoneNumber + " "
						+ emailAddress + " " + emailAddress + " " + apartmentNumber);
				String registered = connect.registerGuest(firstName, lastNmae, personalNumber, phoneNumber,
						emailAddress, apartmentNumber, con);
				writer.println(registered);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
