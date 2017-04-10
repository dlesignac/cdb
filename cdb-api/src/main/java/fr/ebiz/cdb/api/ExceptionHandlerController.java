package fr.ebiz.cdb.api;

import fr.ebiz.cdb.api.exception.InvalidParameterException;
import fr.ebiz.cdb.binding.error.Error;
import fr.ebiz.cdb.binding.error.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    /**
     * Handle invalid parameter exception.
     *
     * @param e exception
     * @return errors
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidParameterException.class)
    public Errors InvalidParametersHandler(InvalidParameterException e) {
        return e.getErrors();
    }

    /**
     * Handle other exceptions.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Errors defaultErrorHandler(Exception e) {
        LOGGER.error("unhandled exception occurred", e);

        Error error = new Error();
        error.setCause("Internal Server Error");
        error.setMessage("Please provide feedback so we can fix this ;)");

        List<Error> list = new ArrayList<>();
        list.add(error);

        Errors errors = new Errors();
        errors.setErrors(list);

        return errors;
    }

}
