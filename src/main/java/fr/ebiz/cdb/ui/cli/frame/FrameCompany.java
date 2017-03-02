package fr.ebiz.cdb.ui.cli.frame;

import fr.ebiz.cdb.model.Company;

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

        String name = this.company.getName();

        String display = "Name         : " + name + "\n";

        System.out.print(display);

        displayOptions();
        displayError();
    }

}
