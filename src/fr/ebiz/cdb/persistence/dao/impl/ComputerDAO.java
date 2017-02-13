package fr.ebiz.cdb.persistence.dao.impl;

import java.sql.Connection;
import java.util.List;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.dao.DAO;

public class ComputerDAO extends DAO<Computer> {

	public ComputerDAO(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Computer find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Computer> fetch() {
		// TODO Auto-generated method stub
		return null;
	}

}
