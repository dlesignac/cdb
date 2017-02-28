package fr.ebiz.cdb.service.validator;

import fr.ebiz.cdb.model.Computer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ComputerValidator {

    /**
     * Checks if a name is valid.
     *
     * @param id string to be be checked
     * @return true if valid, else false
     */
    public static boolean validateId(String id) {
        return id == null || "".equals(id) || id.matches("^\\d+$");
    }

    /**
     * Checks if a name is valid.
     *
     * @param name string to be be checked
     * @return true if valid, else false
     */
    public static boolean validateName(String name) {
        return name != null && !"".equals(name);
    }

    /**
     * Checks if a string can be a valid introduced attribute.
     *
     * @param introduced string to be be checked
     * @return true if valid, else false
     */
    public static boolean validateIntroduced(String introduced) {
        return introduced == null || "".equals(introduced) || isValidDate(introduced);
    }

    /**
     * Checks if a string can be a valid discontinued attribute.
     *
     * @param discontinued string to be be checked
     * @return true if valid, else false
     */
    public static boolean validateDiscontinued(String discontinued) {
        return discontinued == null || "".equals(discontinued) || isValidDate(discontinued);
    }

    /**
     * Checks if a string can be a valid companyId attribute.
     *
     * @param companyId string to be be checked
     * @return true if valid, else false
     */
    public static boolean validateCompanyId(String companyId) {
        return companyId == null || "".equals(companyId) || companyId.matches("^\\d+$");
    }

    /**
     * Checks if a computer is valid.
     *
     * @param computer computer to be be checked
     * @return true if valid, else false
     */
    public static boolean validate(Computer computer) {
        LocalDate introduced = computer.getIntroduced();
        LocalDate discontinued = computer.getDiscontinued();

        return (introduced == null && discontinued == null) ||
                (introduced != null && introduced.isBefore(LocalDate.now()) &&
                        (discontinued == null || (discontinued.isBefore(LocalDate.now()) && discontinued.isAfter(introduced))));
    }

    /**
     * Checks if a string is a valid date.
     *
     * @param text string to be checked
     * @return true if valid, else false
     */
    private static boolean isValidDate(String text) {
        if (text == null || !text.matches("\\d{4}-[01]\\d-[0-3]\\d")) {
            return false;
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(text);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

}
