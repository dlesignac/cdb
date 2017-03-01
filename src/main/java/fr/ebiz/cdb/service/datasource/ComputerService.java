package fr.ebiz.cdb.service.datasource;

import fr.ebiz.cdb.dto.DeleteRequest;
import fr.ebiz.cdb.dto.PageRequest;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.dao.ComputerDAO;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import fr.ebiz.cdb.persistence.exception.QueryException;

import java.sql.Connection;
import java.util.List;

/**
 * Computer service.
 * Gives connection to ComputerDAO and handles transactions.
 */
public enum ComputerService {

    INSTANCE;

    private ConnectionManager connectionManager;
    private IComputerDAO computerDAO;

    /**
     * Constructor.
     */
    ComputerService() {
        this.connectionManager = ConnectionManager.INSTANCE;
        this.computerDAO = ComputerDAO.INSTANCE;
    }

    /**
     * Inserts new computer into datasource.
     *
     * @param computer computer to be inserted
     * @throws DatasourceException an unexpected error occurred
     * @throws QueryException      an unexpected error occurred
     */
    public void createComputer(Computer computer) throws DatasourceException, QueryException {
        Connection connection = connectionManager.getConnection();
        computerDAO.create(connection, computer);
        connectionManager.commitTransaction(connection);
        connectionManager.closeConnection(connection);
    }

    /**
     * Deletes computer into datasource.
     *
     * @param computer computer to be deleted
     * @throws DatasourceException an unexpected error occurred
     * @throws QueryException      an unexpected error occurred
     */
    public void deleteComputer(Computer computer) throws DatasourceException, QueryException {
        Connection connection = connectionManager.getConnection();
        computerDAO.delete(connection, computer);
        connectionManager.commitTransaction(connection);
        connectionManager.closeConnection(connection);
    }

    /**
     * Deletes computers into datasource.
     *
     * @param deleteRequest deleteRequest
     * @throws DatasourceException an unexpected error occurred
     * @throws QueryException      an unexpected error occurred
     */
    public void deleteComputers(DeleteRequest deleteRequest) throws DatasourceException, QueryException {
        Connection connection = connectionManager.getConnection();

        for (Integer id : deleteRequest.getIds()) {
            Computer computer = new Computer();
            computer.setId(id);
            computerDAO.delete(connection, computer);
        }
        connectionManager.commitTransaction(connection);
        connectionManager.closeConnection(connection);
    }

    /**
     * Updates computer into datasource.
     *
     * @param computer computer to be updated
     * @throws DatasourceException an unexpected error occurred
     * @throws QueryException      an unexpected error occurred
     */
    public void updateComputer(Computer computer) throws DatasourceException, QueryException {
        Connection connection = connectionManager.getConnection();
        computerDAO.update(connection, computer);
        connectionManager.commitTransaction(connection);
        connectionManager.closeConnection(connection);
    }

    /**
     * Find computer by id.
     *
     * @param id computer's id
     * @return computer
     * @throws DatasourceException an unexpected error occurred
     * @throws QueryException      an unexpected error occurred
     */
    public Computer findComputer(int id) throws DatasourceException, QueryException {
        Connection connection = connectionManager.getConnection();
        Computer computer = computerDAO.find(connection, id);
        connectionManager.closeConnection(connection);
        return computer;
    }

    /**
     * Gets a frame of computers.
     *
     * @param search  search
     * @param orderBy orderBy
     * @param order   order
     * @param limit   max number of computers
     * @param number  number of requested frame
     * @return frame of computers
     * @throws DatasourceException an unexpected error occurred
     * @throws QueryException      an unexpected error occurred
     */
    public Page<Computer> pageComputers(String search, String orderBy, String order, int limit, int number)
            throws DatasourceException, QueryException {
        Connection connection = connectionManager.getConnection();
        int computersCount = computerDAO.count(connection, search);
        int pageCount = (computersCount + limit - 1) / limit;
        List<Computer> computers = computerDAO.fetch(connection, search, orderBy, order, limit, (number - 1) * limit);
        connectionManager.closeConnection(connection);
        return new Page<>(number, pageCount, limit, computersCount, search, orderBy, computers);
    }

    /**
     * Gets a frame of computers.
     *
     * @param pageRequest pageRequest
     * @return frame of computers
     * @throws DatasourceException an unexpected error occurred
     * @throws QueryException      an unexpected error occurred
     */
    public Page<Computer> pageComputers(PageRequest pageRequest) throws DatasourceException, QueryException {
        int limit = pageRequest.getLimit();
        int number = pageRequest.getNumber();
        String search = pageRequest.getSearch();
        String orderBy = pageRequest.getOrderBy();
        String order = pageRequest.getOrder();
        Connection connection = connectionManager.getConnection();
        int computersCount = computerDAO.count(connection, search);
        int pageCount = (computersCount + limit - 1) / limit;
        List<Computer> computers = computerDAO.fetch(connection, search, orderBy, order, limit, (number - 1) * limit);
        connectionManager.closeConnection(connection);
        return new Page<>(number, pageCount, limit, computersCount, search, orderBy, computers);
    }


}
