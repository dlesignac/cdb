package fr.ebiz.cdb.service.validator;

import fr.ebiz.cdb.dto.ComputerDTO;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ComputerValidator {

    /**
     * Validates a computer DTO.
     *
     * @param computer computer
     * @return errors
     */
    public static List<String> validate(ComputerDTO computer) {
        List<String> errors = new ArrayList<>();

        validateId(errors, computer.getId());
        validateName(errors, computer.getName());
        LocalDate introduced = validateIntroduced(errors, computer.getIntroduced());
        LocalDate discontinued = validateDiscontinued(errors, computer.getDiscontinued());
        validateCompanyId(errors, computer.getCompanyId());

        if (introduced != null && !introduced.isBefore(LocalDate.now())) {
            errors.add("Introduction date must be before now");
        }

        if (discontinued != null && (introduced == null || !discontinued.isAfter(introduced))) {
            errors.add("Discontinuation date must not be before introduction date");
        }

        return errors;
    }

    /**
     * Checks if a name is valid.
     *
     * @param errors errors
     * @param id     string to be be checked
     */
    private static void validateId(List<String> errors, String id) {
        if (id != null && !"".equals(id) & !id.matches("^\\d+$")) {
            errors.add("Id must be an integer");
        }
    }

    /**
     * Checks if a name is valid.
     *
     * @param errors errors
     * @param name   string to be be checked
     */
    private static void validateName(List<String> errors, String name) {
        if (name == null || "".equals(name)) {
            errors.add("Name must not be empty");
        } else if (name.length() > 255) {
            errors.add("Name must be less than 255 characters long");
        }
    }

    /**
     * Checks if a string can be a valid introduced attribute.
     *
     * @param errors     errors
     * @param introduced string to be be checked
     * @return date
     */
    private static LocalDate validateIntroduced(List<String> errors, String introduced) {
        if (introduced != null && !"".equals(introduced)) {
            if (!isValidDate(introduced)) {
                errors.add("Introduction date must match the yyyy-MM-dd format");
            } else {
                try {
                    return LocalDate.parse(introduced);
                } catch (DateTimeParseException e) {
                    errors.add("Introduction date is not valid");
                }
            }
        }

        return null;
    }

    /**
     * Checks if a string can be a valid discontinued attribute.
     *
     * @param errors       errors
     * @param discontinued string to be be checked
     * @return date
     */
    private static LocalDate validateDiscontinued(List<String> errors, String discontinued) {
        if (discontinued != null && !"".equals(discontinued)) {
            if (!isValidDate(discontinued)) {
                errors.add("Discontinuation date must match the yyyy-MM-dd format");
            } else {
                try {
                    return LocalDate.parse(discontinued);
                } catch (DateTimeParseException e) {
                    errors.add("Discontinuation date is not valid");
                }
            }
        }

        return null;
    }

    /**
     * Checks if a string can be a valid companyId attribute.
     *
     * @param errors    errors
     * @param companyId string to be be checked
     */
    private static void validateCompanyId(List<String> errors, String companyId) {
        if (companyId != null && !"".equals(companyId) && !companyId.matches("^\\d+$")) {
            errors.add("Company id must be an integer");
        }
    }

    /**
     * Checks if a string is a valid date.
     *
     * @param text string to be checked
     * @return true if valid, else false
     */
    private static boolean isValidDate(String text) {
        return text != null && text.matches("\\d{4}-[01]\\d-[0-3]\\d");
    }

}
