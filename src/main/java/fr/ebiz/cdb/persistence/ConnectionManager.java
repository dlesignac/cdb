package fr.ebiz.cdb.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Datasource service.
 * Loads connection pool and manages connection attribution.
 */
public enum ConnectionManager {

    INSTANCE;

    private final String PROPERTY_JDBC_DRIVER = "jdbcDriver";
    private final String PROPERTY_JDBC_URL = "jdbcUrl";
    private final String PROPERTY_USER = "user";
    private final String PROPERTY_PASSWORD = "password";
    private final String PROPERTY_POOL_SIZE = "poolsize";
    private final String PROPERTY_AUTOCOMMIT = "autocommit";

    private Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

    private DataSource dataSource;

    /**
     * Loads datasource driver and initiates connection pool.
     */
    ConnectionManager() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("application.properties");

        try {
            Properties properties = new Properties();
            properties.load(propertiesFile);

            Class.forName(properties.getProperty(PROPERTY_JDBC_DRIVER));

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(properties.getProperty(PROPERTY_JDBC_URL));
            config.setUsername(properties.getProperty(PROPERTY_USER));
            config.setPassword(properties.getProperty(PROPERTY_PASSWORD));
            config.setMaximumPoolSize(Integer.parseInt(properties.getProperty(PROPERTY_POOL_SIZE)));
            config.setAutoCommit(Boolean.getBoolean(properties.getProperty(PROPERTY_AUTOCOMMIT)));

            dataSource = new HikariDataSource(config);
        } catch (IOException e) {
            logger.error("could not read properties file", e);
        } catch (ClassNotFoundException e) {
            logger.error("could not load db driver", e);
        }
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
