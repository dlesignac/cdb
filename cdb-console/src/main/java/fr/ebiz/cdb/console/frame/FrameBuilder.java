package fr.ebiz.cdb.console.frame;

import fr.ebiz.cdb.binding.ComputerDTO;
import fr.ebiz.cdb.console.CLIOptions;
import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.core.Page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameBuilder {

    /**
     * Build index frame.
     *
     * @return index frame
     */
    public Frame buildIndex() {
        Frame frame = new Frame();
        frame.setLevels(buildLevels("CDB"));

        List<Option> options = new ArrayList<>();
        options.add(new Option(CLIOptions.LIST_COMPUTERS, "list computers"));
        options.add(new Option(CLIOptions.LIST_COMPANIES, "list companies"));
        options.add(new Option(CLIOptions.CREATE_COMPUTER, "create computer"));
        options.add(new Option(CLIOptions.QUIT, "quit"));
        frame.setOptions(options);

        return frame;
    }

    /**
     * Build computers frame.
     *
     * @param page frame containing computers
     * @return computers frame
     */
    public Frame buildComputers(Page<ComputerDTO> page) {
        FrameComputers frame = new FrameComputers();
        frame.setLevels(buildLevels("CDB", "computers"));
        frame.setPageNumber(page.getNumber());
        frame.setComputers(page.getEntries());

        List<Option> options = new ArrayList<>();
        options.add(new Option(CLIOptions.SHOW, "show computer"));

        if (page.getNumber() > 1) {
            options.add(new Option(CLIOptions.PREVIOUS_PAGE, "previous frame"));
        }

        if (page.getNumber() < page.getLast()) {
            options.add(new Option(CLIOptions.NEXT_PAGE, "next frame"));
        }

        options.add(new Option(CLIOptions.BACK, "back"));
        frame.setOptions(options);

        return frame;
    }

    /**
     * Build computer frame.
     *
     * @param computerDTO computer to display
     * @return computer frame
     */
    public Frame buildComputer(ComputerDTO computerDTO) {
        FrameComputer frame = new FrameComputer();
        frame.setLevels(buildLevels("CDB", "computer", String.valueOf(computerDTO.getId())));
        frame.setComputer(computerDTO);

        List<Option> options = new ArrayList<>();
        options.add(new Option(CLIOptions.EDIT, "edit computer"));
        options.add(new Option(CLIOptions.DELETE, "delete computer"));
        options.add(new Option(CLIOptions.BACK, "back"));
        frame.setOptions(options);

        return frame;
    }

    /**
     * Build computer create frame.
     *
     * @param computerDTO computer to create
     * @return computer create frame
     */
    public Frame buildComputerCreate(ComputerDTO computerDTO) {
        FrameComputerChange frame = new FrameComputerChange();
        frame.setLevels(buildLevels("CDB", "computer", "new"));
        frame.setComputerDTO(computerDTO);

        List<Option> options = new ArrayList<>();
        options.add(new Option(CLIOptions.NEW_NAME, "new name"));
        options.add(new Option(CLIOptions.NEW_INTRODUCED, "new introduced"));
        options.add(new Option(CLIOptions.NEW_DISCONTINUED, "new discontinued"));
        options.add(new Option(CLIOptions.NEW_MANUFACTURER, "new company id"));
        options.add(new Option(CLIOptions.SAVE, "save"));
        options.add(new Option(CLIOptions.CANCEL, "cancel"));
        frame.setOptions(options);

        return frame;
    }

    /**
     * Build computer edit frame.
     *
     * @param computerDTO computer to edit
     * @return computer edit frame
     */
    public Frame buildComputerEdit(ComputerDTO computerDTO) {
        FrameComputerChange frame = new FrameComputerChange();
        frame.setLevels(buildLevels("CDB", "computer", String.valueOf(computerDTO.getId()), "edit"));
        frame.setComputerDTO(computerDTO);

        List<Option> options = new ArrayList<>();
        options.add(new Option(CLIOptions.NEW_NAME, "new name"));
        options.add(new Option(CLIOptions.NEW_INTRODUCED, "new introduced"));
        options.add(new Option(CLIOptions.NEW_DISCONTINUED, "new discontinued"));
        options.add(new Option(CLIOptions.NEW_MANUFACTURER, "new company id"));
        options.add(new Option(CLIOptions.SAVE, "save"));
        options.add(new Option(CLIOptions.CANCEL, "cancel"));
        frame.setOptions(options);

        return frame;
    }

    /**
     * Build companies frame.
     *
     * @param companies companies to display
     * @return companies frame
     */
    public Frame buildCompanies(List<Company> companies) {
        FrameCompanies frame = new FrameCompanies();
        frame.setLevels(buildLevels("CDB", "companies"));
        frame.setCompanies(companies);

        List<Option> options = new ArrayList<>();
        options.add(new Option(CLIOptions.SHOW, "show company"));
        options.add(new Option(CLIOptions.BACK, "back"));
        frame.setOptions(options);

        return frame;
    }

    /**
     * Build company frame.
     *
     * @param company company to display
     * @return company frame
     */
    public Frame buildCompany(Company company) {
        FrameCompany frame = new FrameCompany();
        frame.setLevels(buildLevels("CDB", "company", String.valueOf(company.getId())));
        frame.setCompany(company);

        List<Option> options = new ArrayList<>();
        options.add(new Option(CLIOptions.DELETE, "delete company"));
        options.add(new Option(CLIOptions.BACK, "back"));
        frame.setOptions(options);

        return frame;
    }

    /**
     * Build levels component.
     *
     * @param levels levels
     * @return levels component
     */
    private List<String> buildLevels(String... levels) {
        List<String> l = new ArrayList<>();
        Collections.addAll(l, levels);
        return l;
    }
}