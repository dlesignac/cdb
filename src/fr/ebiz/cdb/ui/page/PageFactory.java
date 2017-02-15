package fr.ebiz.cdb.ui.page;

import java.util.List;

public interface PageFactory {

	Page getInfoPage(String info);

	Page getListPage(List entities);

}
