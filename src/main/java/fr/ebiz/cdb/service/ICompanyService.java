package fr.ebiz.cdb.service;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.service.exception.TransactionFailedException;

import java.util.List;

public interface ICompanyService {

    /**
     * Deletes company into datasource.
     *
     * @param company company to be deleted
     * @throws TransactionFailedException an unexpected error occurred
     */
    void delete(Company company) throws TransactionFailedException;

    /**
     * Find company by id.
     *
     * @param id company's id
     * @return company
     * @throws TransactionFailedException an unexpected error occurred
     */
    Company find(int id) throws TransactionFailedException;

    /**
     * Fetches companies.
     *
     * @return companies
     * @throws TransactionFailedException an unexpected error occurred
     */
    List<Company> list() throws TransactionFailedException;

}
