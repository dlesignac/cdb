package fr.ebiz.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Database connection holder. Holds unique connection to database.
 */
public enum DBConnection {

    INSTANCE;

    private Logger logger = LoggerFactory.getLogger(DBConnection.class);

    private Connection connection = null;
    private String url = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false";
    private String user = "admincdb";
    private String password = "qwerty1234";

    /**
     * Private constructor in order to make DBConnection a singleton.
     */
    DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("could not open connection with database", e);
        } catch (ClassNotFoundException e) {
            logger.error("did not find jdbc driver", e);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

}
