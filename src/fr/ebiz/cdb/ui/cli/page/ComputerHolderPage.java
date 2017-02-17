package fr.ebiz.cdb.ui.cli.page;

import fr.ebiz.cdb.model.Computer;

/**
 * Computer Holder Page interface. Used by CLI controller to retrieve Computer
 * info.
 */
public interface ComputerHolderPage extends ContentPage {

	Computer getComputer();

}
