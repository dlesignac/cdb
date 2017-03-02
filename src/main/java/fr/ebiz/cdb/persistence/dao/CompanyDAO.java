package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.util.QueryBuilder;
import fr.ebiz.cdb.persistence.exception.DAOQueryException;
import fr.ebiz.cdb.persistence.util.mapper.CompanyRSMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Company DAO.
 */
public enum CompanyDAO implements ICompanyDAO {

    INSTANCE;

    private ConnectionManager connectionManager = ConnectionManager.INSTANCE;

    @Override
    public void delete(Connection connection, Company company) throws DAOQueryException {
        String query = new QueryBuilder()
                .deleteFrom("company")
                .where("id = ?")
                .build();

        try (PreparedStatement statement = connectionManager.prepareStatement(connection, query, company.getId())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOQueryException("could not delete company " + company.toString(), e);
        }
    }

    @Override
    public Company find(Connection connection, int id) throws DAOQueryException {
        String query = new QueryBuilder()
                .select("*")
                .from("company")
                .where("id = ?")
                .build();

        try (PreparedStatement statement = connectionManager.prepareStatement(connection, query, id)) {
            ResultSet rs = statement.executeQuery();
            return new CompanyRSMapper().mapToOne(rs);
        } catch (SQLException e) {
            throw new DAOQueryException("could not find company by id " + id, e);
        }
    }

    @Override
    public List<Company> fetch(Connection connection) throws DAOQueryException {
        String query = new QueryBuilder()
                .select("*")
                .from("company")
                .build();

        try (PreparedStatement statement = connectionManager.prepareStatement(connection, query)) {
            ResultSet rs = statement.executeQuery();
            return new CompanyRSMapper().mapToMany(rs);
        } catch (SQLException e) {
            throw new DAOQueryException("could not fetch companies", e);
        }
    }

}
