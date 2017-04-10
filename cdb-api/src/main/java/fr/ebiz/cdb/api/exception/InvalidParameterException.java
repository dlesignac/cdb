package fr.ebiz.cdb.api.exception;

import fr.ebiz.cdb.binding.error.Errors;

public class InvalidParameterException extends RuntimeException {

    private Errors errors;

    /**
     * Constructor.
     *
     * @param errors errors
     */
    public InvalidParameterException(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
