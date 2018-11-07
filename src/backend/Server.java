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
			Socket socket = server.accept();

			// Write to users
			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter writer = new PrintWriter(out, true);

			// Read information from users
			InputStreamReader input = new InputStreamReader(socket.getInputStream());
			BufferedReader reader = new BufferedReader(input);

			// Här Servern tar emot datan från Login classen
			String username = reader.readLine();
			String password = reader.readLine();
			System.out.println(username + " " + password);
			Connection con = Connect.getConnection();
			Connect connect = new Connect();
			int state = connect.loginAdmin(username, password, con);
			writer.println(Integer.toString(state));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
