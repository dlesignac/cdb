package fr.ebiz.cdb.persistence;

import fr.ebiz.cdb.persistence.exception.DatasourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class ConnectionManager {

    private static final ThreadLocal<Connection> CONNECTIONS = new ThreadLocal<>();

    private Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

    @Autowired
    private DataSource dataSource;

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
