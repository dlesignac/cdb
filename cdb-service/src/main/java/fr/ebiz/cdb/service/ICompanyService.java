package fr.ebiz.cdb.service;

import fr.ebiz.cdb.core.Company;

import java.util.List;

public interface ICompanyService {

    /**
     * Deletes company into datasource.
     *
     * @param company company to be deleted
     */
    void delete(Company company);

    /**
     * Find company by id.
     *
     * @param id company's id
     * @return company
     */
    Company find(int id);

    /**
     * Fetches companies.
     *
     * @return companies
     */
    List<Company> list();

}
