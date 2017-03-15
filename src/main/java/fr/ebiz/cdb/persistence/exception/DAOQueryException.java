package fr.ebiz.cdb.persistence.exception;

import java.sql.SQLException;

public class DAOQueryException extends Exception {

    /**
     * Constructor.
     *
     * @param message message
     * @param e       exception
     */
    public DAOQueryException(String message, SQLException e) {
        super(message, e);
    }

    /**
     * Constructor.
     *
     * @param message message
     */
    public DAOQueryException(String message) {
        super(message);
    }

}
