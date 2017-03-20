package fr.ebiz.cdb.mapper.dto;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;

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
     */
    public static Computer mapFromDTO(ComputerDTO computerDTO) {
        Integer id = computerDTO.getId();
        String name = computerDTO.getName();
        String introduced = computerDTO.getIntroduced();
        String discontinued = computerDTO.getDiscontinued();
        Integer companyId = computerDTO.getCompanyId();

        Computer computer = new Computer();
        computer.setName(name);
        computer.setId(id);

        if (introduced != null && !"".equals(introduced)) {
            computer.setIntroduced(LocalDate.parse(introduced));
        }

        if (discontinued != null && !"".equals(discontinued)) {
            computer.setDiscontinued(LocalDate.parse(discontinued));
        }

        if (companyId != null) {
            Company company = new Company();
            company.setId(companyId);
            computer.setManufacturer(company);
        }

        return computer;
    }

    /**
     * Maps a computer into a DTO.
     *
     * @param computer computer
     * @return computer
     */
    public static ComputerDTO mapToDTO(Computer computer) {
        Integer id = computer.getId();
        String name = computer.getName();
        String introduced = computer.getIntroduced() == null ? "" : computer.getIntroduced().toString();
        String discontinued = computer.getDiscontinued() == null ? "" : computer.getDiscontinued().toString();
        Integer companyId = computer.getManufacturer() == null ? null : computer.getManufacturer().getId();

        return new ComputerDTO.Builder()
                .id(id)
                .name(name)
                .introduced(introduced)
                .discontinued(discontinued)
                .companyId(companyId)
                .build();
    }

}