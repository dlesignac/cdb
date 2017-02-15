package fr.ebiz.cdb.ui.page;

import java.util.List;
import java.util.Map;

public interface PageFactory {

	Page getInfoPage(Map<String, String> options, String info);

	Page getListPage(Map<String, String> options, List entities);

	Page getDetailPage(Map<String, String> options, Map<String, String> details);

}
