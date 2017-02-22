package fr.ebiz.cdb.mapper;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.validator.ValidatorComputer;
import fr.ebiz.cdb.service.validator.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * Computer mapper.
 */
public abstract class MapperComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperComputer.class);

    /**
     * Maps string parameters to a computer.
     *
     * @param name         computer name parameter
     * @param introduced   computer introduced parameter
     * @param discontinued computer discontinued parameter
     * @param companyId    computer's manufacturer id parameter
     * @return computer
     * @throws ValidationException one or more entries are invalid
     */
    public static Computer map(String name, String introduced, String discontinued, String companyId) throws ValidationException {
        boolean valid = true;

        if (!ValidatorComputer.validateName(name)) {
            LOGGER.warn("tried to set computer name :" + name);
            valid = false;
        }

        if (!ValidatorComputer.validateIntroduced(introduced)) {
            LOGGER.warn("tried to set computer introduced :" + introduced);
            valid = false;
        }

        if (!ValidatorComputer.validateDiscontinued(discontinued)) {
            LOGGER.warn("tried to set computer discontinued :" + discontinued);
            valid = false;
        }

        if (!ValidatorComputer.validateCompanyId(companyId)) {
            LOGGER.warn("tried to set computer companyId :" + companyId);
            valid = false;
        }

        if (valid) {
            Computer computer = new Computer();
            computer.setName(name);

            if (introduced != null && !"".equals(introduced)) {
                computer.setIntroduced(LocalDate.parse(introduced));
            }

            if (discontinued != null && !"".equals(discontinued)) {
                computer.setDiscontinued(LocalDate.parse(discontinued));
            }

            if (companyId != null && !"".equals(companyId)) {
                Company company = new Company();
                company.setId(Integer.parseInt(companyId));
                computer.setManufacturer(company);
            }

            if (ValidatorComputer.validate(computer)) {
                return computer;
            }
            // TODO warn invalid
            // TODO check companyId
        }

        throw new ValidationException();
    }

}
