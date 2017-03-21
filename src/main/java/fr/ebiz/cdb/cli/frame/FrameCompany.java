package fr.ebiz.cdb.cli.frame;

import fr.ebiz.cdb.core.model.Company;

/**
 * Frame company.
 */
public class FrameCompany extends Frame {

    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public void display() {
        displayHeader();
        System.out.print("Name         : " + company.getName() + "\n");
        displayOptions();
        displayError();
    }

}