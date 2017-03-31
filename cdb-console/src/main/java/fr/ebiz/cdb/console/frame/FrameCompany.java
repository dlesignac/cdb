package fr.ebiz.cdb.console.frame;

import fr.ebiz.cdb.core.Company;

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