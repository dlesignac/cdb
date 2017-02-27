package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.ebiz.cdb.persistence.QueryBuilder;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
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
    public Company find(Connection connection, int id) throws DatasourceException, QueryException {
        String query = new QueryBuilder()
                .select("*")
                .from(TABLE_COMPANY)
                .where("id = ?")
                .build();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            return new CompanyRSMapper().mapToOne(rs);
        } catch (SQLException e) {
            logger.error("could not find company", e);
            throw new QueryException();
        }
    }

    @Override
    public int count(Connection connection) throws DatasourceException, QueryException {
        String query = new QueryBuilder()
                .select("count(id)")
                .from(TABLE_COMPANY)
                .build();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();

            if (rs.first()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("could not count companies", e);
        }

        throw new QueryException();
    }

    @Override
    public List<Company> fetch(Connection connection) throws DatasourceException, QueryException {
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

}
