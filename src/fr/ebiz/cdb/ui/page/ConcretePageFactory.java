package fr.ebiz.cdb.ui.page;

import java.util.List;

public class ConcretePageFactory implements PageFactory {

	private static final String CONCRETE_HEADER =
			"------------------------------\n" +
			"       COMPUTER DATABASE\n" +
			"------------------------------\n";

	@Override
	public Page getInfoPage(String info) {
		return new ConcreteInfoPage(CONCRETE_HEADER, info);
	}

	@Override
	public Page getListPage(List entities) {
		return new ConcreteListPage(CONCRETE_HEADER, entities);
	}

}
