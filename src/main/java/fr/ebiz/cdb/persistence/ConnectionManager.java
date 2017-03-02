package fr.ebiz.cdb.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Datasource service.
 * Loads connection pool and manages connection attribution.
 */
public enum ConnectionManager {

    INSTANCE;

    private Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

    private DataSource dataSource;

    /**
     * Loads datasource driver and initiates connection pool.
     */
    ConnectionManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("could not load db driver", e);
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db?useSSL=false");
        config.setUsername("admincdb");
        config.setPassword("qwerty1234");

        config.setMaximumPoolSize(10);
        config.setAutoCommit(false);

        dataSource = new HikariDataSource(config);
    }

    /**
     * Gets a connection.
     *
     * @return connection
     * @throws DatasourceException an unexpected error occurred
     */
    public Connection getConnection() throws DatasourceException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("could not get connection from datasource", e);
            throw new DatasourceException();
        }
    }

    /**
     * Commits transaction.
     *
     * @param connection connection to commit
     * @throws DatasourceException an unexpected error occurred
     */
    public void commit(Connection connection) throws DatasourceException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("could not commit transaction", e);
            throw new DatasourceException();
        }
    }

    /**
     * Rollback transaction.
     *
     * @param connection connection to rollback
     */
    public void rollback(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("could not rollback transaction", e);
        }
    }

    /**
     * Closes connection.
     *
     * @param connection connection to commit
     */
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("could not close connection", e);
        }
    }

    /**
     * Prepares statement.
     *
     * @param connection connection
     * @param query      query
     * @param objects    objects
     * @return PreparedStatement
     * @throws SQLException an unexpected error occurred
     */
    public PreparedStatement prepareStatement(Connection connection, String query, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }

        return preparedStatement;
    }

}
