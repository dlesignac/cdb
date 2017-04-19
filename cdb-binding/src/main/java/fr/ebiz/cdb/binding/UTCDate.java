package fr.ebiz.cdb.binding;

import fr.ebiz.cdb.validator.UTCDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UTCDateValidator.class)
@Documented
public @interface UTCDate {

    /**
     * @return message
     */
    String message() default "{}";

    /**
     * @return groups
     */
    Class<?>[] groups() default {};

    /**
     * @return payload
     */
    Class<? extends Payload>[] payload() default {};
}
