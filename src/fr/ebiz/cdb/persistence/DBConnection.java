package fr.ebiz.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection = null;
	private String url; // TODO
	private String user; // TODO
	private String password; // TODO
	
	private DBConnection() {
		try {
			DBConnection.connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static Connection getInstance() {
		if (DBConnection.connection == null) {
			new DBConnection();
		}
		
		return DBConnection.connection;
	}

}
