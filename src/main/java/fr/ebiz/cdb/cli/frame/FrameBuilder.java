package fr.ebiz.cdb.cli.frame;

import fr.ebiz.cdb.cli.CLIOptions;
import fr.ebiz.cdb.core.dto.ComputerDTOMapper;
import fr.ebiz.cdb.core.model.Company;
import fr.ebiz.cdb.core.model.Computer;
import fr.ebiz.cdb.core.model.Page;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Frame builder.
 */
public class FrameBuilder {

    /**
     * Builds index frame.
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
     * Builds computers frame.
     *
     * @param page frame containing computers
     * @return computers frame
     */
    public Frame buildComputers(Page<Computer> page) {
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
     * Builds computer frame.
     *
     * @param computer computer to display
     * @return computer frame
     */
    public Frame buildComputer(Computer computer) {
        FrameComputer frame = new FrameComputer();
        frame.setLevels(buildLevels("CDB", "computer", String.valueOf(computer.getId())));
        frame.setComputer(computer);

        List<Option> options = new ArrayList<>();
        options.add(new Option(CLIOptions.EDIT, "edit computer"));
        options.add(new Option(CLIOptions.DELETE, "delete computer"));
        options.add(new Option(CLIOptions.BACK, "back"));
        frame.setOptions(options);

        return frame;
    }

    /**
     * Builds computer create frame.
     *
     * @param computer computer to create
     * @return computer create frame
     */
    public Frame buildComputerCreate(Computer computer) {
        FrameComputerChange frame = new FrameComputerChange();
        frame.setLevels(buildLevels("CDB", "computer", "new"));
        frame.setComputerDTO(ComputerDTOMapper.mapToDTO(computer));

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
     * Builds computer edit frame.
     *
     * @param computer computer to edit
     * @return computer edit frame
     */
    public Frame buildComputerEdit(Computer computer) {
        FrameComputerChange frame = new FrameComputerChange();
        frame.setLevels(buildLevels("CDB", "computer", String.valueOf(computer.getId()), "edit"));
        frame.setComputerDTO(ComputerDTOMapper.mapToDTO(computer));

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
     * Builds companies frame.
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
     * Builds company frame.
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
     * Builds levels component.
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