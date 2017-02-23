package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;

/**
 * DAO interface.
 * Abstracts interactions with a data source.
 */
abstract class DAO {

    Connection connection = null;

    /**
     * Constructor.
     *
     * @param connection connection to use
     */
    DAO(Connection connection) {
        this.connection = connection;
    }

}
