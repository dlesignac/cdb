package fr.ebiz.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection = null;
	private String url = "jdbc:mysql://localhost:3306/computer-database-db";
	private String user = "admincdb";
	private String password = "qwerty1234";
	
	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DBConnection.connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
