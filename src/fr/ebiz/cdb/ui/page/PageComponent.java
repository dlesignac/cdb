package fr.ebiz.cdb.ui.page;

/**
 * Page Component interface. Abstracts a page component that must be computed
 * into a string in order to be displayed by a FullPage.
 */
public interface PageComponent {

	String toDisplay();

}
