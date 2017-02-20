package fr.ebiz.cdb.persistence.dao;

import org.junit.Assert;
import org.junit.Test;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.Page;

public class ComputerDAOTest {

	@Test
	public void testFetch() {
		DAO<Computer> dao = new DAOFactory(DBConnection.getInstance()).getComputerDAO();
		Page<Computer> computers = dao.fetchAll();

		if (computers == null || computers.getEntries() == null || computers.getEntries().size() != 10) {
			Assert.fail("null");
		}
	}

}
