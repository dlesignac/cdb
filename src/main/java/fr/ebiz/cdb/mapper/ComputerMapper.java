package fr.ebiz.cdb.mapper;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.validator.ComputerValidator;
import fr.ebiz.cdb.service.validator.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * Computer mapper.
 */
public abstract class ComputerMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);

    /**
     * Maps string parameters to a computer.
     *
     * @param computerDTO computerDTO
     * @return computer
     * @throws ValidationException one or more entries are invalid
     */
    public static Computer map(ComputerDTO computerDTO) throws ValidationException {
        boolean valid = true;

        String id = computerDTO.getId();
        String name = computerDTO.getName();
        String introduced = computerDTO.getIntroduced();
        String discontinued = computerDTO.getDiscontinued();
        String companyId = computerDTO.getCompanyId();

        if (!ComputerValidator.validateId(id)) {
            LOGGER.warn("tried to set computer id : " + id);
            valid = false;
        }

        if (!ComputerValidator.validateName(name)) {
            LOGGER.warn("tried to set computer name : " + name);
            valid = false;
        }

        if (!ComputerValidator.validateIntroduced(introduced)) {
            LOGGER.warn("tried to set computer introduced : " + introduced);
            valid = false;
        }

        if (!ComputerValidator.validateDiscontinued(discontinued)) {
            LOGGER.warn("tried to set computer discontinued : " + discontinued);
            valid = false;
        }

        if (!ComputerValidator.validateCompanyId(companyId)) {
            LOGGER.warn("tried to set computer companyId : " + companyId);
            valid = false;
        }

        if (valid) {
            Computer computer = new Computer();
            computer.setName(name);

            if (id != null && !"".equals(id)) {
                computer.setId(Integer.parseInt(id));
            }

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

            if (ComputerValidator.validate(computer)) {
                return computer;
            }

            LOGGER.warn("tried to insert invalid computer : " + computer);
        }

        throw new ValidationException();
    }

}
