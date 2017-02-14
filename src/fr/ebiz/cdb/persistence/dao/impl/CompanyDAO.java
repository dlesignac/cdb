package fr.ebiz.cdb.persistence.dao.impl;

import java.sql.Connection;
import java.util.List;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.dao.DAO;

public class CompanyDAO extends DAO<Company> {

	public CompanyDAO(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Company find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> fetch() {
		// TODO Auto-generated method stub
		return null;
	}

}
