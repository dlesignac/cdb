package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;

import fr.ebiz.cdb.persistence.Page;

/**
 * DAO interface. Abstracts interactions with a data source through an SQL
 * connection.
 */
public abstract class DAO<T> {

    protected Connection connection = null;

    /**
     * DAO should not be instantiated without setting attributes.
     * @param connection
     *            connection used by the DAO
     */
    public DAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Persists objects into data source.
     * @param obj
     *            object to be persisted
     * @return boolean success
     */
    public abstract boolean create(T obj);

    /**
     * Deletes object entry into data source.
     * @param obj
     *            object
     * @return succes
     */
    public abstract boolean delete(T obj);

    /**
     * Updates object entry into data source.
     * @param obj
     *            object
     * @return succes
     */
    public abstract boolean update(T obj);

    /**
     * Gets object by id from data source.
     * @param id
     *            object's id
     * @return entry
     */
    public abstract T find(int id);

    /**
     * Fetches all object entries from data source.
     * @param limit
     *            size of resultset
     * @param offset
     *            offset pages
     * @return pager on entry list
     */
    public abstract Page<T> fetch(int limit, int offset);

    /**
     * Fetches all object entries from data source.
     * @return pager on entry list
     */
    public abstract Page<T> fetchAll();

}
