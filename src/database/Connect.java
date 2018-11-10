package database;

import java.sql.*;
import java.util.ArrayList;

public class Connect {
	static final String connString = "jdbc:mysql://localhost:3306/";
	static final String database = "OurHotel";
	static final String timezoneFix = "?useLegacyDatetimeCode=false&serverTimezone=Europe/Stockholm";
	static final String password = "asdasd";
	

	public static Connection getConnection() {

		try {

			String url = connString + database + timezoneFix;
			Connection con = DriverManager.getConnection(url, "root", password);
			if (con != null) {
				System.out.println("uppkopplad");
			}
			return con;
		}

		catch (Exception e) {
			System.err.println("Uppkopplingen misslyckades");

		}
		return null;
	}

	public int loginAdmin(String username, String password, Connection con) {

		String query = "select * from Hotel_Admin where Username =? and  Pword=?";
		// den här stringen ska skickas till Login klassen för att se om det blev nån
		// uppkoppling eller inte
		int state;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return state = 1;
			} else {
				return state = 0;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return state = -1;
	}

	public String registerGuest(String fname, String lname, String personalNumber, String phoneNumber,
			String emailAddress, String apartmentNumber, Connection con) {

		String query = "insert into hyresgaster(firstName,lastname,personNumber,phone_number,email,ApartmentNumber) values (?,?,?,?,?,?)";
		String state;

		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setString(3, personalNumber);
			statement.setString(4, phoneNumber);
			statement.setString(5, emailAddress);
			statement.setString(6, apartmentNumber);

			statement.execute();
			return state = "ok";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return state = "notok";
		}

	}

	/**
	 * 
	 * @return
	 */
	public String[][] printGuestsList() {

		String query = "select * from hyresgaster";
		// two demensional arraylist
		String[][] guests = new String[7][10];
		try (Connection conn = Connect.getConnection(); PreparedStatement pst = conn.prepareStatement(query);) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				for (int i = 0; i < guests.length; i++) {
					for (int j = 0; j < 7; j++) {
						guests[i][j] = rs.getString(j + 1);
					}
				}
			}
			return guests;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return guests;
	}
}