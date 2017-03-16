package fr.ebiz.cdb.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class ConnectionManager {

    private static final String FILE_PROPERTIES = "application.properties";

    private static final String PROPERTY_JDBC_DRIVER = "jdbc.driver";
    private static final String PROPERTY_DATASOURCE_URL = "datasource.url";
    private static final String PROPERTY_DATASOURCE_USERNAME = "datasource.username";
    private static final String PROPERTY_DATASOURCE_PASSWORD = "datasource.password";
    private static final String PROPERTY_DATASOURCE_POOLSIZE = "datasource.poolsize";
    private static final String PROPERTY_DATASOURCE_AUTOCOMMIT = "datasource.autocommit";

    private static final ThreadLocal<Connection> CONNECTIONS = new ThreadLocal<>();

    private Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

    private DataSource dataSource;

    /**
     * Loads datasource driver and initiates connection pool.
     */
    public ConnectionManager() {
        ClassLoader classLoader = ConnectionManager.class.getClassLoader();
        InputStream globalPropertiesFile = classLoader.getResourceAsStream(FILE_PROPERTIES);

        try {
            Properties properties = new Properties();
            properties.load(globalPropertiesFile);
            loadProperties(properties);
        } catch (IOException e) {
            logger.error("could not read properties file", e);
        }
    }

    /**
     * Load properties.
     *
     * @param properties properties
     */
    private void loadProperties(Properties properties) {
        try {
            Class.forName(properties.getProperty(PROPERTY_JDBC_DRIVER));
        } catch (ClassNotFoundException e) {
            logger.error("could not load db driver", e);
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty(PROPERTY_DATASOURCE_URL));
        config.setUsername(properties.getProperty(PROPERTY_DATASOURCE_USERNAME));
        config.setPassword(properties.getProperty(PROPERTY_DATASOURCE_PASSWORD));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty(PROPERTY_DATASOURCE_POOLSIZE)));
        config.setAutoCommit(Boolean.getBoolean(properties.getProperty(PROPERTY_DATASOURCE_AUTOCOMMIT)));

        dataSource = new HikariDataSource(config);
    }

    /**
     * Gets a connection.
     *
     * @return connection
     * @throws DatasourceException an unexpected error occurred
     */
    public Connection getConnection() throws DatasourceException {
        if (CONNECTIONS.get() == null) {
            try {
                CONNECTIONS.set(dataSource.getConnection());
            } catch (SQLException e) {
                logger.error("could not get connection from datasource", e);
                throw new DatasourceException();
            }
        }

        return CONNECTIONS.get();
    }

    /**
     * Commits transaction.
     *
     * @throws DatasourceException an unexpected error occurred
     */
    public void commit() throws DatasourceException {
        try {
            CONNECTIONS.get().commit();
        } catch (SQLException e) {
            logger.error("could not commit transaction", e);
            throw new DatasourceException();
        }
    }

    /**
     * Rollback transaction.
     */
    public void rollback() {
        try {
            CONNECTIONS.get().commit();
        } catch (SQLException e) {
            logger.error("could not rollback transaction", e);
        }
    }

    /**
     * Closes connection.
     */
    public void close() {
        try {
            CONNECTIONS.get().close();
            CONNECTIONS.remove();
        } catch (SQLException e) {
            logger.error("could not close connection", e);
        }
    }

    /**
     * Prepares statement.
     *
     * @param query   query
     * @param objects objects
     * @return PreparedStatement
     * @throws SQLException an unexpected error occurred
     */
    public PreparedStatement prepareStatement(String query, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = CONNECTIONS.get().prepareStatement(query);

        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }

        return preparedStatement;
    }

}
