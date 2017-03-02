package fr.ebiz.cdb.mapper.dto;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.mapper.exception.ValidationException;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.validator.ComputerValidator;

import java.time.LocalDate;

/**
 * Computer mapper.
 */
public abstract class ComputerDTOMapper {

    /**
     * Maps a DTO into a computer.
     *
     * @param computerDTO computerDTO
     * @return computer
     * @throws ValidationException one or more entries are invalid
     */
    public static Computer mapDTO(ComputerDTO computerDTO) throws ValidationException {
        String id = computerDTO.getId();
        String name = computerDTO.getName();
        String introduced = computerDTO.getIntroduced();
        String discontinued = computerDTO.getDiscontinued();
        String companyId = computerDTO.getCompanyId();

        if (!ComputerValidator.validateId(id)) {
            throw new ValidationException("tried to set computer id : " + id);
        }

        if (!ComputerValidator.validateName(name)) {
            throw new ValidationException("tried to set computer name : " + name);
        }

        if (!ComputerValidator.validateIntroduced(introduced)) {
            throw new ValidationException("tried to set computer introduced : " + introduced);
        }

        if (!ComputerValidator.validateDiscontinued(discontinued)) {
            throw new ValidationException("tried to set computer discontinued : " + discontinued);
        }

        if (!ComputerValidator.validateCompanyId(companyId)) {
            throw new ValidationException("tried to set computer companyId : " + companyId);
        }

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

        throw new ValidationException("tried to insert invalid computer : " + computer);
    }

}
