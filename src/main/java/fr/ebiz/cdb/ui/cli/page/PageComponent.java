package fr.ebiz.cdb.ui.cli.page;

/**
 * Page Component interface. Abstracts a page component that must be computed
 * into a string in order to be displayed by a FullPage.
 */
public interface PageComponent {

    /**
     * Computes data into a string that can be displayed.
     * @return string to be displayed
     */
    String toDisplay();

}
