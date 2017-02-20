package fr.ebiz.cdb.ui.cli.page;

/**
 * Page interface. Abstracts a CLI Page.
 */
public interface Page {

    /**
     * Displays page on stdout.
     */
    void display();

    /**
     * Sets error page component for next display.
     * @param error
     *            error to be displayed.
     */
    void setError(String error);

}
