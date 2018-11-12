package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import database.Connect;

public class Server {

	public static void main(String[] args) throws Exception {
		try (ServerSocket server = new ServerSocket(2277); Connection con = Connect.getConnection();) {
			System.out.println("server is running");
			Socket socket;
			Connect connect = new Connect();
			int state = Integer.MIN_VALUE;

			// This while loop listens to the LoginView
			while (state != 1) {
				socket = server.accept();
				OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
				PrintWriter writer = new PrintWriter(out, true);

				InputStreamReader input = new InputStreamReader(socket.getInputStream());
				BufferedReader reader = new BufferedReader(input);

				String username = reader.readLine();
				String password = reader.readLine();
				state = connect.loginAdmin(username, password, con);
				writer.println(Integer.toString(state));
			}
			String btnPressed = "";
			// This while loop listens to the EnterData view
			while (true) {
				socket = server.accept();

				OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
				PrintWriter writer = new PrintWriter(out, true);

				InputStreamReader input = new InputStreamReader(socket.getInputStream());
				BufferedReader reader = new BufferedReader(input);
				btnPressed = reader.readLine();
				ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());

				// Switch statement decides to do when when a certain button is pressed
				switch (btnPressed) {
				case "spara":
					String firstName = reader.readLine();
					String lastName = reader.readLine();
					String personalNumber = reader.readLine();
					String phoneNumber = reader.readLine();
					String emailAddress = reader.readLine();
					String apartmentNumber = reader.readLine();
					int registered = connect.registerGuest(firstName, lastName, personalNumber, phoneNumber,
							emailAddress, apartmentNumber, con);
					writer.println(registered);
					// TODO remove
					System.out.println(registered);
					break;
				case "data":
					List<Hyresgast> hyresgaster = connect.getHyresgaster(con);
					objOut.writeObject(hyresgaster);
					break;
				case "emptyApartments":
					List<EmptyApartment> emptyapartments = (List<EmptyApartment>) connect.getEmptyapartments(con);
					objOut.writeObject(emptyapartments);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
