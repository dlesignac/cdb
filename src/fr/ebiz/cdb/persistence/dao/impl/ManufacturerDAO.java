package fr.ebiz.cdb.persistence.dao.impl;

import java.sql.Connection;
import java.util.List;

import fr.ebiz.cdb.model.Manufacturer;
import fr.ebiz.cdb.persistence.dao.DAO;

public class ManufacturerDAO extends DAO<Manufacturer> {

	public ManufacturerDAO(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Manufacturer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Manufacturer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Manufacturer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Manufacturer find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manufacturer> fetch() {
		// TODO Auto-generated method stub
		return null;
	}

}
