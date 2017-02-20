package fr.ebiz.cdb.ui.cli.page;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.ebiz.cdb.model.Company;

/**
 * Companies List Page. Computes data into a string to be printed for Company
 * listing.
 */
public class CompaniesPage implements ContentPage {

    private List<Company> companies;

    /**
     * Constructor.
     * @param companies
     *            list of companies
     */
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
