package fr.ebiz.cdb.persistence.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.dao.DAO;

/**
 * Computer DAO. Interacts with a data source to persist and retrieve Computer
 * objects.
 */
public class ComputerDAO extends DAO<Computer> {

	private Logger logger = LoggerFactory.getLogger(DAO.class);

	private static final String SQL_TABLE_COMPUTER = "computer";
	private static final String SQL_COLUMN_ID = "id";
	private static final String SQL_COLUMN_NAME = "name";
	private static final String SQL_COLUMN_INTRODUCED = "introduced";
	private static final String SQL_COLUMN_DISCONTINUED = "discontinued";
	private static final String SQL_COLUMN_COMPANY_ID = "company_id";

	public ComputerDAO(Connection connection) {
		super(connection);
	}

	/**
	 * Persist new Computer object.
	 */
	@Override
	public boolean create(Computer obj) {
		try {
			String query = "INSERT INTO " + SQL_TABLE_COMPUTER + "(" + 
					SQL_COLUMN_NAME + ", " +
					SQL_COLUMN_INTRODUCED + "," +
					SQL_COLUMN_DISCONTINUED + "," +
					SQL_COLUMN_COMPANY_ID +
					") VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, obj.getName());
			
			LocalDate introduced = obj.getIntroduced();
			LocalDate discontinued = obj.getDiscontinued();
			Company company = obj.getManufacturer();
			
			if (introduced == null) {
				statement.setNull(2, Types.DATE);
			} else {
				statement.setDate(2, Date.valueOf(introduced));
			}
			
			if (discontinued == null) {
				statement.setNull(3, Types.DATE);
			} else {
				statement.setDate(3, Date.valueOf(discontinued));
			}
			
			if (company == null) {
				statement.setInt(4, Types.INTEGER);
			} else {
				statement.setInt(4, company.getId());
			}
			
			statement.executeUpdate();
			this.connection.commit();
		} catch (SQLException e) {
			logger.error("could not insert Computer object", e);
			return false;
		}

		return true;
	}

	/**
	 * Delete Computer object from data source.
	 */
	@Override
	public boolean delete(Computer obj) {
		try {
			String query = "DELETE FROM " + SQL_TABLE_COMPUTER + " WHERE " + SQL_COLUMN_ID + " = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, obj.getId());

			statement.executeUpdate();
			this.connection.commit();
		} catch (SQLException e) {
			logger.error("could not delete Computer object", e);
			return false;
		}

		return true;
	}

	/**
	 * Update Computer object in data source.
	 */
	@Override
	public boolean update(Computer obj) {
		try {
			String query = "UPDATE " + SQL_TABLE_COMPUTER + " SET " + SQL_COLUMN_NAME + " = ?, " + SQL_COLUMN_INTRODUCED
					+ " = ?, " + SQL_COLUMN_DISCONTINUED + " = ?, " + SQL_COLUMN_COMPANY_ID + " = ?" + " WHERE "
					+ SQL_COLUMN_ID + " = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, obj.getName());
			statement.setDate(2, Date.valueOf(obj.getIntroduced()));
			statement.setDate(3, Date.valueOf(obj.getDiscontinued()));
			statement.setInt(4, obj.getManufacturer().getId());
			statement.setInt(5, obj.getId());

			statement.executeUpdate();
			this.connection.commit();
		} catch (SQLException e) {
			logger.error("could not update Computer object", e);
			return false;
		}

		return true;
	}

	/**
	 * Retrieve Computer object from data source given an id.
	 */
	@Override
	public Computer find(int id) {
		Computer computer = null;

		try {
			String query = "SELECT * FROM " + SQL_TABLE_COMPUTER +
					" WHERE " + SQL_COLUMN_ID + " = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();
			computer = new Computer();
			computer.setId(rs.getInt(SQL_COLUMN_ID));
			computer.setName(rs.getString(SQL_COLUMN_NAME));
			computer.setIntroduced(rs.getDate(SQL_COLUMN_INTRODUCED).toLocalDate());
			computer.setDiscontinued(rs.getDate(SQL_COLUMN_DISCONTINUED).toLocalDate());
			// TODO company

		} catch (SQLException e) {
			logger.error("could not find Computer object", e);
		}

		return computer;
	}

	/**
	 * Fetch all Computers objects available in data source.
	 */
	@Override
	public List<Computer> fetch() {
		List<Computer> computers = null;

		try {
			String query = "SELECT * FROM " + SQL_TABLE_COMPUTER;
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(query);
			computers = new ArrayList<>();

			while (rs.next()) {
				Computer computer = new Computer();
				computer.setId(rs.getInt(SQL_COLUMN_ID));
				computer.setName(rs.getString(SQL_COLUMN_NAME));

				Date introduced = rs.getDate(SQL_COLUMN_INTRODUCED);
				Date discontinued = rs.getDate(SQL_COLUMN_DISCONTINUED);

				computer.setIntroduced(introduced == null ? null : introduced.toLocalDate());
				computer.setDiscontinued(discontinued == null ? null : discontinued.toLocalDate());
				// TODO company

				computers.add(computer);
			}
		} catch (SQLException e) {
			logger.error("could not fetch Computer objects", e);
		}

		return computers;
	}

}
