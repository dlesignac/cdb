package fr.ebiz.cdb.persistence.mapper;

import fr.ebiz.cdb.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ebiz on 27/02/17.
 */
public class CompanyRSMapper extends RSMapper<Company> {

    @Override
    protected Company map(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setId(rs.getInt("id"));
        company.setName(rs.getString("name"));
        return company;
    }

}
