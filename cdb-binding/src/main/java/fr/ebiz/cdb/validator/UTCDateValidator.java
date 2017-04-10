package fr.ebiz.cdb.validator;

import fr.ebiz.cdb.binding.UTCDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UTCDateValidator implements ConstraintValidator<UTCDate, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UTCDateValidator.class);

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(UTCDate utcDate) {

    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        boolean valid = object == null || isValidDate(object);

        if (!valid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext
                    .buildConstraintViolationWithTemplate("{dateValidator.invalidDate}")
                    .addConstraintViolation();
        }

        return valid;
    }

    /**
     * Check if date is valid.
     *
     * @param dateString dateString
     * @return isValidDate
     */
    private boolean isValidDate(String dateString) {
        try {
            LocalDate.parse(dateString, FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
