package fr.ebiz.cdb.persistence.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.dao.DAO;

public class ComputerDAO extends DAO<Computer> {
	
	private static final String SQL_TABLE_COMPUTER = "computer";
	private static final String SQL_COLUMN_ID = "id";
	private static final String SQL_COLUMN_NAME = "name";
	private static final String SQL_COLUMN_INTRODUCED = "introduced";
	private static final String SQL_COLUMN_DISCONTINUED = "discontinued";
	private static final String SQL_COLUMN_COMPANY_ID = "company_id";

	public ComputerDAO(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Computer obj) {
		try {
			String query = "INSERT INTO ? VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, SQL_TABLE_COMPUTER);
			statement.setString(2, obj.getName());
			statement.setDate(3, Date.valueOf(obj.getIntroduced()));
			statement.setDate(4, Date.valueOf(obj.getDiscontinued()));
			statement.setInt(5, obj.getManufacturer().getId());
			
			statement.executeUpdate();
			this.connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean delete(Computer obj) {
		try {
			String query = "DELETE FROM ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, SQL_TABLE_COMPUTER);
			statement.setInt(2, obj.getId());
			
			statement.executeUpdate();
			this.connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean update(Computer obj) {
		try {
			String query = "UPDATE ? SET ? = ?, ? = ?, ? = ?, ? = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, SQL_TABLE_COMPUTER);
			statement.setString(2, SQL_COLUMN_NAME);
			statement.setString(3, obj.getName());
			statement.setString(4, SQL_COLUMN_INTRODUCED);
			statement.setDate(5, Date.valueOf(obj.getIntroduced()));
			statement.setString(6, SQL_COLUMN_DISCONTINUED);
			statement.setDate(7, Date.valueOf(obj.getDiscontinued()));
			statement.setString(8, SQL_COLUMN_COMPANY_ID);
			statement.setInt(9, obj.getManufacturer().getId());
			statement.setInt(10, obj.getId());
			
			statement.executeUpdate();
			this.connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public Computer find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Computer> fetch() {
		List<Computer> computers = null;
		
		try {
			String query = "SELECT * FROM ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, SQL_TABLE_COMPUTER);
			
			ResultSet rs = statement.executeQuery();
			computers = new ArrayList<>();
			
			while (rs.next()) {
				Computer computer = new Computer();
				computer.setId(rs.getInt(SQL_COLUMN_ID));
				computer.setName(rs.getString(SQL_COLUMN_NAME));
				computer.setIntroduced(rs
						.getDate(SQL_COLUMN_INTRODUCED)
						.toLocalDate());
				computer.setDiscontinued(rs
						.getDate(SQL_COLUMN_DISCONTINUED)
						.toLocalDate());
				
				// TODO company
				
				computers.add(computer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return computers;
	}

}
