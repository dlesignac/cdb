package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.Page;
import fr.ebiz.cdb.persistence.exception.PersistenceException;
import org.junit.Test;

public class TestComputerDAO {

    @Test
    public void TestFetchAll() throws PersistenceException {
        IDAOComputer dao = new DAOFactory(DBConnection.getInstance()).getComputerDAO();
        Page<Computer> page = dao.fetch(10, 0);

        for (Computer computer : page.getEntries()) {
            System.out.println(computer);
        }
    }

}
