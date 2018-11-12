package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import backend.EmptyApartment;
import backend.Hyresgast;

public class Connect {
	static final String connString = "jdbc:mysql://localhost:3306/";
	static final String database = "OurHotel";
	static final String timezoneFix = "?useLegacyDatetimeCode=false&serverTimezone=Europe/Stockholm";
	static final String password = "Happy4sure";

	// Create connection method
	public static Connection getConnection() {

		try {
			String url = connString + database + timezoneFix;
			Connection con = DriverManager.getConnection(url, "root", password);
			if (con != null) {
				System.out.println("uppkopplad");
			}
			return con;
		} catch (Exception e) {
			System.err.println("Uppkopplingen misslyckades");
		}
		return null;
	}

	public int loginAdmin(String username, String password, Connection con) {
		/**
		 * state variable to be sent to the client to them if the login succeeded or not
		 */

		String query = "select * from Hotel_Admin where Username =? and  Pword=?";
		int state;
		try (PreparedStatement pst = con.prepareStatement(query);) {
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return state = 1;
			} else {
				return state = 0;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return state = -1;
	}

	public int registerGuest(String fname, String lname, String personalNumber, String phoneNumber,
			String emailAddress, String apartmentNumber, Connection con) {
		/**
		 * The method that registers new guests
		 */

		String query = "insert into hyresgaster(firstName,lastname,personNumber,phone_number,email,ApartmentNumber)"
				+ " values (?,?,?,?,?,?)";
		int state;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setString(3, personalNumber);
			statement.setString(4, phoneNumber);
			statement.setString(5, emailAddress);
			statement.setString(6, apartmentNumber);
			statement.execute();
			return state = 1;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return state = -1;
	}

	public List<Hyresgast> getHyresgaster(Connection con) throws Exception {
		/**
		 * This method beings all the information about guests
		 */
		List<Hyresgast> hyresgaster = new ArrayList<>();
		String query = "select * from hyresgaster";
		try (Statement stmt = con.prepareStatement(query)) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String personNumber = rs.getString("personNumber");
				String phone_number = rs.getString("phone_number");
				String email = rs.getString("email");
				String apartmentNumber = rs.getString("apartmentNumber");
				Hyresgast tempHyresgast = new Hyresgast(id, firstName, lastName,
						personNumber, phone_number, email,apartmentNumber);
				hyresgaster.add(tempHyresgast);
			}
			rs.close();
			return hyresgaster;
		}
	}

	public List<EmptyApartment> getEmptyapartments(Connection con) throws Exception {
		/**
		 * THis method gets the information about the emty apartmenst
		 */
		List<EmptyApartment> emptyApartments = new ArrayList<>();
		String query = "select a.id, a.address, a.postal_code, a.city, a.size, a.rooms, a.rent from apartments as a "
				+ "WHERE id NOT IN (SELECT apartmentnumber FROM hyresgaster)";
		try (PreparedStatement stmt = con.prepareStatement(query);) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("a.id");
				String address = rs.getString("a.address");
				String postal_code = rs.getString("a.postal_code");
				String city = rs.getString("a.city");
				Double size = rs.getDouble("size");
				int rooms = rs.getInt("a.rooms");
				Double rent = rs.getDouble("a.rent");
				EmptyApartment tempEmptyApartment = new EmptyApartment(id, address, postal_code, 
						city, size, rooms,
						rent);
				emptyApartments.add(tempEmptyApartment);
			}
			rs.close();
			return emptyApartments;
		}
	}
}