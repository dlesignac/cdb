package fr.ebiz.cdb.service.datasource;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.dao.DAOFactory;
import fr.ebiz.cdb.persistence.dao.IDAOCompany;
import fr.ebiz.cdb.persistence.dao.IDAOComputer;
import fr.ebiz.cdb.persistence.exception.PersistenceException;

import java.util.List;

/**
 * Datasource service.
 */
public class ServiceDatasource {

    private static ServiceDatasource instance;

    private IDAOCompany daoCompany;
    private IDAOComputer daoComputer;

    /**
     * Constructor.
     */
    private ServiceDatasource() {
        DAOFactory daoFactory = new DAOFactory(DBConnection.getInstance());
        this.daoCompany = daoFactory.getCompanyDAO();
        this.daoComputer = daoFactory.getComputerDAO();
    }

    /**
     * Inserts new computer into datasource.
     *
     * @param computer computer to be inserted
     */
    public void createComputer(Computer computer) {
        this.daoComputer.create(computer);
    }

    /**
     * Fetches computers.
     *
     * @param limit  max number of computers
     * @param offset number of computers to skip
     * @return computers
     * @throws PersistenceException unexpected error occurred
     */
    private List<Computer> listComputers(int limit, int offset) throws PersistenceException {
        return this.daoComputer.fetch(limit, offset);
    }

    /**
     * Gets computers count.
     *
     * @return computers cout
     * @throws PersistenceException unexpected error occurred
     */
    public int countComputers() throws PersistenceException {
        return this.daoComputer.count();
    }

    /**
     * Gets a page of computers.
     *
     * @param limit  max number of computers
     * @param number number of requested page
     * @return page of computers
     * @throws PersistenceException unexpected error occurred
     */
    public Page<Computer> pageComputers(int limit, int number) throws PersistenceException {
        int computersCount = countComputers();
        int pageCount = (computersCount + limit - 1) / limit;
        List<Computer> computers = listComputers(limit, number - 1);
        return new Page<>(limit, pageCount, number, computers);
    }

    /**
     * Fetches companies.
     *
     * @return companies
     * @throws PersistenceException unexpected error occurred
     */
    public List<Company> listCompanies() throws PersistenceException {
        return this.daoCompany.fetch();
    }


    /**
     * Gets unique instance of ServiceDatasource.
     *
     * @return ServiceDatasource
     */
    public static ServiceDatasource getInstance() {
        if (instance == null) {
            instance = new ServiceDatasource();
        }

        return instance;
    }

}
