package fr.ebiz.cdb.ui.page;

import java.util.List;
import java.util.Map;

public class ConcretePageFactory implements PageFactory {

	private static final String CONCRETE_HEADER = "------------------------------\n" + "       COMPUTER DATABASE\n"
			+ "------------------------------\n";

	@Override
	public Page getInfoPage(Map<String, String> options, String info) {
		return new ConcreteInfoPage(CONCRETE_HEADER, options, info);
	}

	@Override
	public Page getListPage(Map<String, String> options, List entities) {
		return new ConcreteListPage(CONCRETE_HEADER, options, entities);
	}

}
