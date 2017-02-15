package fr.ebiz.cdb.ui.cli;

import java.util.List;

import fr.ebiz.cdb.ui.page.Page;

public interface CLIPageBuilder {

	Page getIndexPage();

	Page getListPage(List entities);

}
