package fr.ebiz.cdb.service.exception;

public class TransactionFailedException extends Exception {

    public static final String FAILURE_QUERYING = "an error occurred while querying datasource";
    public static final String FAILURE_OPENING = "an error occurred while opening datasource";

    /**
     * Constructor.
     *
     * @param message message
     * @param e       e
     */
    public TransactionFailedException(String message, Exception e) {
        super(message, e);
    }

}