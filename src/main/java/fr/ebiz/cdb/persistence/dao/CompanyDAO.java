package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.Page;

/**
 * Company DAO. Interacts with a data source to persist and retrieve Company
 * objects.
 */
class CompanyDAO extends DAO<Company> {

    private Logger logger = LoggerFactory.getLogger(DAO.class);

    private static final String SQL_TABLE_COMPANY = "company";
    private static final String SQL_COLUMN_ID = "id";
    private static final String SQL_COLUMN_NAME = "name";

    /**
     * CompanyDAO should not be instantiated without parameters.
     * @param connection
     *            connection to use
     */
    CompanyDAO(Connection connection) {
        super(connection);
    }

    /**
     * Persist new Company object.
     */
    @Override
    public boolean create(Company obj) {
        try {
            String query = "INSERT INTO " + SQL_TABLE_COMPANY + "(" + SQL_COLUMN_NAME + ") VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, obj.getName());

            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            logger.error("could not insert Company object", e);
            return false;
        }

        return true;
    }

    /**
     * Delete Company object from data source.
     */
    @Override
    public boolean delete(Company obj) {
        try {
            String query = "DELETE FROM " + SQL_TABLE_COMPANY + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, obj.getId());

            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            logger.error("could not delete Company object", e);
            return false;
        }

        return true;
    }

    /**
     * Update Company object in data source.
     */
    @Override
    public boolean update(Company obj) {
        try {
            String query = "UPDATE " + SQL_TABLE_COMPANY + " SET " + SQL_COLUMN_NAME + " = ? WHERE " + SQL_COLUMN_ID
                    + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getId());

            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            logger.error("could not update Company object", e);
            return false;
        }

        return true;
    }

    /**
     * Retrieve Company object from data source given an id.
     */
    @Override
    public Company find(int id) {
        Company company = null;

        try {
            String query = "SELECT * FROM " + SQL_TABLE_COMPANY + " WHERE " + SQL_COLUMN_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.first()) {
                company = new Company();
                company.setId(rs.getInt(SQL_COLUMN_ID));
                company.setName(rs.getString(SQL_COLUMN_NAME));
            }
        } catch (SQLException e) {
            logger.error("could not find Company object", e);
        }

        return company;
    }

    /**
     * Fetch all Company objects available in data source.
     */
    @Override
    public List<Company> fetch() {
        List<Company> companies = null;

        try {
            String query = "SELECT * FROM " + SQL_TABLE_COMPANY;
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            companies = new ArrayList<>();

            while (rs.next()) {
                Company company = new Company();
                company.setId(rs.getInt(SQL_COLUMN_ID));
                company.setName(rs.getString(SQL_COLUMN_NAME));

                companies.add(company);
            }
        } catch (SQLException e) {
            logger.error("could not fetch Company objects", e);
        }

        return companies;
    }

    @Override
    public Page<Company> fetch(int limit, int offset) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Company> fetchAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
