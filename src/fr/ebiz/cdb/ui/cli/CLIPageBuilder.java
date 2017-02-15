package fr.ebiz.cdb.ui.cli;

import java.util.List;
import java.util.Map;

import fr.ebiz.cdb.ui.page.Page;

public interface CLIPageBuilder {

	Page getIndexPage();

	Page getListPage(List entities);

	Page getDetailPage(Map<String, String> details);

}
