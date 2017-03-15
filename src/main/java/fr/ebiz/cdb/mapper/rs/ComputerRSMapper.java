package fr.ebiz.cdb.mapper.rs;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Computer RS mapper.
 */
public class ComputerRSMapper extends RSMapper<Computer> {

    private static final String COLUMN_ID = "computer_id";
    private static final String COLUMN_NAME = "computer_name";
    private static final String COLUMN_INTRODUCED = "introduced";
    private static final String COLUMN_DISCONTINUED = "discontinued";
    private static final String COLUMN_COMPANY_ID = "company_id";
    private static final String COLUMN_COMPANY_NAME = "company_name";

    @Override
    protected Computer map(ResultSet rs) throws SQLException {
        Computer computer = new Computer();
        computer.setId(rs.getInt(COLUMN_ID));
        computer.setName(rs.getString(COLUMN_NAME));

        Date introduced = rs.getDate(COLUMN_INTRODUCED);
        Date discontinued = rs.getDate(COLUMN_DISCONTINUED);

        computer.setIntroduced(introduced == null ? null : introduced.toLocalDate());
        computer.setDiscontinued(discontinued == null ? null : discontinued.toLocalDate());

        int companyId = rs.getInt(COLUMN_COMPANY_ID);

        if (companyId != 0) {
            Company company = new Company();
            company.setId(companyId);
            company.setName(rs.getString(COLUMN_COMPANY_NAME));
            computer.setManufacturer(company);
        }

        return computer;
    }

}
