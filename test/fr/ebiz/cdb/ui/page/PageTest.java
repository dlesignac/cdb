package fr.ebiz.cdb.ui.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ebiz.cdb.service.pager.Pager;
import fr.ebiz.cdb.service.pager.PagerIndexOutOfBoundsException;
import fr.ebiz.cdb.ui.page.ConcretePageFactory;
import fr.ebiz.cdb.ui.page.Page;
import fr.ebiz.cdb.ui.page.PageFactory;

public class PageTest {

	public static void main(String[] args) {
		List<String> l = new ArrayList<>();

		for (int i = 0; i < 25; i++) {
			l.add("entry");
		}

		Pager<String> pager = new Pager<>(l);

		Map<String, String> options = new HashMap<>();
		options.put("<", "back");

		PageFactory pageFactory = new ConcretePageFactory();
		Page page;

		try {
			pager.next();

			page = pageFactory.getListPage(pager.next());

			options.put("<n>", "print entry n");

			if (pager.hasPrevious()) {
				options.put("^", "previous page");
			}

			if (pager.hasNext()) {
				options.put("v", "next page");
			}

			page.setOptions(options);
		} catch (PagerIndexOutOfBoundsException e) {
			page = pageFactory.getInfoPage("");
			page.setOptions(options);
			page.setError(e.getMessage());
		}

		page.display();
	}

}
