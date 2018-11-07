package database;

import java.sql.*;

public class Connect {
	static final String connString = "jdbc:mysql://localhost:3306/";
	static final String database = "OurHotel";
	static final String timezoneFix = "?useLegacyDatetimeCode=false&serverTimezone=Europe/Stockholm";
	static final String password = "asdasdasd";
	private Connection con = null;

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
}