package fr.ebiz.cdb.mapper.rs;

import fr.ebiz.cdb.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Company RS mapper.
 */
public class CompanyRSMapper extends RSMapper<Company> {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    @Override
    protected Company map(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setId(rs.getInt(COLUMN_ID));
        company.setName(rs.getString(COLUMN_NAME));
        return company;
    }

}
