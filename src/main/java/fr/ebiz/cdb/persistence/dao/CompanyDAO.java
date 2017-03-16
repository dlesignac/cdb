package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.mapper.rs.CompanyRSMapper;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.exception.DAOQueryException;
import fr.ebiz.cdb.persistence.util.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CompanyDAO implements ICompanyDAO {

    @Autowired
    private ConnectionManager connectionManager;

    @Override
    public void delete(Company company) throws DAOQueryException {
        String query = new QueryBuilder()
                .deleteFrom("company")
                .where("id = ?")
                .build();

        try (PreparedStatement statement = connectionManager.prepareStatement(query, company.getId())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOQueryException("could not delete company " + company.toString(), e);
        }
    }

    @Override
    public Company find(int id) throws DAOQueryException {
        String query = new QueryBuilder()
                .select("*")
                .from("company")
                .where("id = ?")
                .build();

        try (PreparedStatement statement = connectionManager.prepareStatement(query, id)) {
            ResultSet rs = statement.executeQuery();
            return new CompanyRSMapper().mapToOne(rs);
        } catch (SQLException e) {
            throw new DAOQueryException("could not find company by id " + id, e);
        }
    }

    @Override
    public List<Company> fetch() throws DAOQueryException {
        String query = new QueryBuilder()
                .select("*")
                .from("company")
                .build();

        try (PreparedStatement statement = connectionManager.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            return new CompanyRSMapper().mapToMany(rs);
        } catch (SQLException e) {
            throw new DAOQueryException("could not fetch companies", e);
        }
    }

}
