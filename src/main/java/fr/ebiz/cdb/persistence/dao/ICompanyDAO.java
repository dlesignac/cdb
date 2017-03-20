package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.model.Company;

import java.util.List;

/**
 * CompanyDAO interface.
 */
public interface ICompanyDAO {

    /**
     * Deletes company.
     *
     * @param company to be deleted
     */
    void delete(Company company);

    /**
     * Finds company by its id.
     *
     * @param id company's id
     * @return company
     */
    Company find(int id);

    /**
     * Fetches companies.
     *
     * @return entries
     */
    List<Company> fetch();
}