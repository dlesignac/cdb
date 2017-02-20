package fr.ebiz.cdb.service.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import fr.ebiz.cdb.model.Computer;

public class ComputerValidator {

    /**
     * Checks if a string can be a valid computer name.
     * @param name
     *            name to be checked
     * @return true if the string is valid, else false
     */
    public static boolean validateName(String name) {
        return true;
    }

    /**
     * Checks if a string can be parsed to a valid introduction date.
     * @param str
     *            string to be checked
     * @return true if the string is valid, else false
     */
    public static boolean validateIntroduced(String str) {
        return str == null || (isValidDate(str) && LocalDate.parse(str).isBefore(LocalDate.now()));
    }

    /**
     * Checks if a string can be parsed to a valid discontinuation date.
     * @param str
     *            string to be checked
     * @param computer
     *            computer to be checked
     * @return true if the string is valid, else false
     */
    public static boolean validateDiscontinued(String str, Computer computer) {
        LocalDate date = LocalDate.parse(str);
        return str == null || (computer.getIntroduced() != null && isValidDate(str) && date.isBefore(LocalDate.now())
                && date.isAfter(computer.getIntroduced()));
    }

    /**
     * Checks if a string is a valid date.
     * @param text
     *            string to be checked
     * @return true if string is a valid date, else false
     */
    public static boolean isValidDate(String text) {
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
