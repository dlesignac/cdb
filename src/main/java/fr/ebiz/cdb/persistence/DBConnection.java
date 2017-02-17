package fr.ebiz.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Database connection holder.
 * Holds unique connection to database.
 */
public class DBConnection {
	
	private Logger logger = LoggerFactory.getLogger(DBConnection.class);

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
			logger.error("could not open connection with database", e);
		} catch (ClassNotFoundException e) {
			logger.error("did not find jdbc driver", e);
		}
	}
	
	/**
	 * Returns unique instance of connection to database.
	 */
	public static Connection getInstance() {
		if (DBConnection.connection == null) {
			new DBConnection();
		}
		
		return DBConnection.connection;
	}

}
