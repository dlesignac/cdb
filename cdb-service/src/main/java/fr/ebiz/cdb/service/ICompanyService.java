package fr.ebiz.cdb.service;

import fr.ebiz.cdb.core.Company;

import java.util.List;

public interface ICompanyService {

    /**
     * Delete company into datasource.
     *
     * @param id company to be deleted
     * @return status
     */
    int delete(int id);

    /**
     * Find company by id.
     *
     * @param id company's id
     * @return company
     */
    Company find(int id);

    /**
     * Fetch companies.
     *
     * @return companies
     */
    List<Company> list();

}
