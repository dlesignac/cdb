package fr.ebiz.cdb.ui.page;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.ebiz.cdb.model.Company;

public class CompaniesPage extends ContentPage {

	private List<Company> companies;

	public CompaniesPage(List<Company> companies) {
		this.companies = companies;
	}

	@Override
	public String toDisplay() {
		String display = "";

		for (Company company : this.companies) {
			display += StringUtils.leftPad(String.valueOf(company.getId()), 6);
			display += ". ";
			display += company.getName();
			display += "\n";
		}

		return display;
	}

}