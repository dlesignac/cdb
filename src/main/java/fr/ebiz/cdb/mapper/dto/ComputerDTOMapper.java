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
        String id = computerDTO.getId();
        String name = computerDTO.getName();
        String introduced = computerDTO.getIntroduced();
        String discontinued = computerDTO.getDiscontinued();
        String companyId = computerDTO.getCompanyId();

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

        return computer;
    }

    /**
     * Maps a computer into a DTO.
     *
     * @param computer computer
     * @return computer
     */
    public static ComputerDTO mapToDTO(Computer computer) {
        String id = String.valueOf(computer.getId());
        String name = computer.getName();
        String introduced = computer.getIntroduced() == null ? "" : computer.getIntroduced().toString();
        String discontinued = computer.getDiscontinued() == null ? "" : computer.getDiscontinued().toString();
        String companyId = computer.getManufacturer() == null ? "" : String.valueOf(computer.getManufacturer().getId());

        return new ComputerDTO.Builder(name)
                .id(id)
                .introduced(introduced)
                .discontinued(discontinued)
                .companyId(companyId)
                .build();
    }

}
