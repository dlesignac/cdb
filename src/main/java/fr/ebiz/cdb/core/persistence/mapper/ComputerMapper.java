package fr.ebiz.cdb.core.persistence.mapper;

import fr.ebiz.cdb.core.model.Company;
import fr.ebiz.cdb.core.model.Computer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComputerMapper implements RowMapper<Computer> {

    private static final String COLUMN_ID = "computer_id";
    private static final String COLUMN_NAME = "computer_name";
    private static final String COLUMN_INTRODUCED = "introduced";
    private static final String COLUMN_DISCONTINUED = "discontinued";
    private static final String COLUMN_COMPANY_ID = "company_id";
    private static final String COLUMN_COMPANY_NAME = "company_name";

    @Override
    public Computer mapRow(ResultSet resultSet, int i) throws SQLException {
        Computer computer = new Computer();
        computer.setId(resultSet.getInt(COLUMN_ID));
        computer.setName(resultSet.getString(COLUMN_NAME));

        Date introduced = resultSet.getDate(COLUMN_INTRODUCED);
        Date discontinued = resultSet.getDate(COLUMN_DISCONTINUED);

        computer.setIntroduced(introduced == null ? null : introduced.toLocalDate());
        computer.setDiscontinued(discontinued == null ? null : discontinued.toLocalDate());

        int companyId = resultSet.getInt(COLUMN_COMPANY_ID);

        if (companyId != 0) {
            Company company = new Company();
            company.setId(companyId);
            company.setName(resultSet.getString(COLUMN_COMPANY_NAME));
            computer.setManufacturer(company);
        }

        return computer;
    }

}
