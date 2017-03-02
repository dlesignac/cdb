package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.ebiz.cdb.persistence.QueryBuilder;
import fr.ebiz.cdb.persistence.exception.QueryException;
import fr.ebiz.cdb.persistence.mapper.CompanyRSMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;

/**
 * Company DAO.
 */
public enum CompanyDAO implements ICompanyDAO {

    INSTANCE;

    private static final String TABLE_COMPANY = "company";

    private Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

    @Override
    public void delete(Connection connection, Company company) throws QueryException {
        String query = new QueryBuilder()
                .deleteFrom("company")
                .where("id = ?")
                .build();

        Object[] args = {company.getId()};

        try (PreparedStatement statement = prepare(connection, query, args)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not delete company", e);
            throw new QueryException();
        }
    }

    @Override
    public Company find(Connection connection, int id) throws QueryException {
        String query = new QueryBuilder()
                .select("*")
                .from(TABLE_COMPANY)
                .where("id = ?")
                .build();

        Object[] args = {id};

        try (PreparedStatement statement = prepare(connection, query, args)) {
            ResultSet rs = statement.executeQuery();
            return new CompanyRSMapper().mapToOne(rs);
        } catch (SQLException e) {
            logger.error("could not find company", e);
            throw new QueryException();
        }
    }

    @Override
    public List<Company> fetch(Connection connection) throws QueryException {
        String query = new QueryBuilder()
                .select("*")
                .from(TABLE_COMPANY)
                .build();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            return new CompanyRSMapper().mapToMany(rs);
        } catch (SQLException e) {
            logger.error("could not find companies", e);
            throw new QueryException();
        }
    }

    /**
     * Prepares statement.
     *
     * @param connection connection
     * @param query      query
     * @param objects    objects
     * @return PreparedStatement
     * @throws SQLException an unexpected error occurred
     */
    private PreparedStatement prepare(Connection connection, String query, Object[] objects)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }

        return preparedStatement;
    }

}
