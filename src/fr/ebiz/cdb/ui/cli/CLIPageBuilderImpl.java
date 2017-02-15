package fr.ebiz.cdb.ui.cli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ebiz.cdb.ui.page.ConcretePageFactory;
import fr.ebiz.cdb.ui.page.Page;

public class CLIPageBuilderImpl implements CLIPageBuilder {

	@Override
	public Page getIndexPage() {
		Map<String, String> options = new HashMap<>();
		options.put(CLIMenu.INDEX_COMPANY_LIST, "List companies");
		options.put(CLIMenu.INDEX_COMPUTER_LIST, "List computers");
		options.put(CLIMenu.INDEX_QUIT, "Quit");

		return new ConcretePageFactory().getInfoPage(options, "");
	}

	@Override
	public Page getListPage(List entities) {
		Map<String, String> options = new HashMap<>();
		options.put(CLIMenu.LISTPAGE_BACK, "Back");

		return new ConcretePageFactory().getListPage(options, entities);
	}

}
