package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;
import java.util.List;

/**
 * DAO interface. Abstracts interactions with a data source through an SQL
 * connection.
 */
public abstract class DAO<T> {

	protected Connection connection = null;

	public DAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Persists object into data source.
	 */
	public abstract boolean create(T obj);

	/**
	 * Deletes object entry into data source.
	 */
	public abstract boolean delete(T obj);

	/**
	 * Updates object entry into data source.
	 */
	public abstract boolean update(T obj);

	/**
	 * Gets object by id from data source.
	 */
	public abstract T find(int id);

	/**
	 * Fetches all object entries from data source.
	 */
	public abstract List<T> fetch();

}
