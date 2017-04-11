package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.core.Company;

import java.util.List;

public interface ICompanyDAO {

    /**
     * Delete company.
     *
     * @param id to be deleted
     */
    int delete(int id);

    /**
     * Find company by its id.
     *
     * @param id company's id
     * @return company
     */
    Company find(int id);

    /**
     * Fetch companies.
     *
     * @return entries
     */
    List<Company> fetch();

}