package fr.ebiz.cdb.persistence.mapper;

import fr.ebiz.cdb.model.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements RowMapper<Company> {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    @Override
    public Company mapRow(ResultSet resultSet, int i) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getInt(COLUMN_ID));
        company.setName(resultSet.getString(COLUMN_NAME));
        return company;
    }

}
