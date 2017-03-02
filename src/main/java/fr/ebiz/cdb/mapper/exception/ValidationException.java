package fr.ebiz.cdb.mapper.exception;

/**
 * Validation exception.
 */
public class ValidationException extends Exception {

    /**
     * Constructor.
     *
     * @param message message
     */
    public ValidationException(String message) {
        super(message);
    }
}
