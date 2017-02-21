package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ebiz.cdb.persistence.exception.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;

/**
 * Company DAO.
 */
class DAOCompany extends DAO implements IDAOCompany {

    private Logger logger = LoggerFactory.getLogger(DAO.class);

    /**
     * Constructor.
     *
     * @param connection connection to use
     */
    DAOCompany(Connection connection) {
        super(connection);
    }

    @Override
    public Company find(int id) throws PersistenceException {
        String query = "SELECT * FROM company WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.first()) {
                Company company = new Company();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));

                return company;
            }
        } catch (SQLException e) {
            logger.error("could not find company", e);
        }

        throw new PersistenceException();
    }

    @Override
    public int count() throws PersistenceException {
        String query = "SELECT count(id) FROM company";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();

            if (rs.first()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("could not count companies", e);
        }

        throw new PersistenceException();
    }

    @Override
    public List<Company> fetch() throws PersistenceException {
        String query = "SELECT id, name FROM company";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            List<Company> companies = new ArrayList<>();

            while (rs.next()) {
                Company company = new Company();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));

                companies.add(company);
            }

            return companies;
        } catch (SQLException e) {
            logger.error("could not find companies", e);
        }

        throw new PersistenceException();
    }

}
