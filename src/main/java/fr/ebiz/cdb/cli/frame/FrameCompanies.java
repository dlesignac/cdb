package fr.ebiz.cdb.cli.frame;

import fr.ebiz.cdb.core.model.Company;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class FrameCompanies extends Frame {

    private List<Company> companies;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public void display() {
        displayHeader();

        String display = "";

        for (Company company : companies) {
            display += StringUtils.leftPad(String.valueOf(company.getId()), 6);
            display += ". ";
            display += company.getName();
            display += "\n";
        }

        System.out.print(display);

        displayOptions();
        displayError();
    }

}