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

    @Override
    protected Computer map(ResultSet rs) throws SQLException {
        Computer computer = new Computer();
        computer.setId(rs.getInt("computer_id"));
        computer.setName(rs.getString("computer_name"));

        Date introduced = rs.getDate("introduced");
        Date discontinued = rs.getDate("discontinued");

        computer.setIntroduced(introduced == null ? null : introduced.toLocalDate());
        computer.setDiscontinued(discontinued == null ? null : discontinued.toLocalDate());

        int companyId = rs.getInt("company_id");

        if (companyId != 0) {
            Company company = new Company();
            company.setId(companyId);
            company.setName(rs.getString("company_name"));
            computer.setManufacturer(company);
        }

        return computer;
    }

}
