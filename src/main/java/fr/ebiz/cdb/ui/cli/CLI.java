package fr.ebiz.cdb.ui.cli;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import fr.ebiz.cdb.persistence.exception.DatasourceException;
import fr.ebiz.cdb.persistence.exception.QueryException;
import fr.ebiz.cdb.service.datasource.CompanyService;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.Page;
import fr.ebiz.cdb.service.validator.ComputerValidator;
import fr.ebiz.cdb.ui.cli.frame.Frame;
import fr.ebiz.cdb.ui.cli.frame.FrameBuilder;
import fr.ebiz.cdb.ui.cli.frame.FrameCompany;
import fr.ebiz.cdb.ui.cli.frame.FrameComputer;
import fr.ebiz.cdb.ui.cli.frame.FrameComputerChange;
import fr.ebiz.cdb.ui.cli.frame.FrameComputers;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;

/**
 * Command Line Interface.
 */
public class CLI {

    private static Logger logger = LoggerFactory.getLogger(CLI.class);

    /**
     * CLI entry point.
     *
     * @param args entry point arguments
     */
    public static void main(String[] args) {
        new CLI().loop();
    }

    private CompanyService companyService;
    private ComputerService computerService;
    private Scanner in;
    private Frame frame;
    private CLIStatus status;


    /**
     * Default constructor.
     */
    private CLI() {
        this.companyService = CompanyService.INSTANCE;
        this.computerService = ComputerService.INSTANCE;
        this.in = new Scanner(System.in);
        this.frame = new FrameBuilder().buildIndex();
        this.status = CLIStatus.INDEX;
    }


    /**
     * Main loop. Prints pages and waits for user inputs.
     */
    private void loop() {
        while (this.status != CLIStatus.EXIT) {
            this.frame.display();

            try {
                listen();
            } catch (Exception e) {
                this.frame.setError("An error occurred");
                logger.error("caught exception while running CLI", e);
            }
        }

        this.in.close();
    }


    /**
     * Waits for and manages user inputs.
     */
    private void listen() {
        String[] input = this.in.nextLine().split(" ");

        switch (this.status) {
            case INDEX:
                doIndex(input);
                break;
            case COMPUTERS:
                doComputers(input);
                break;
            case COMPUTER:
                doComputer(input);
                break;
            case COMPANIES:
                doCompanies(input);
                break;
            case COMPANY:
                doCompany(input);
                break;
            case COMPUTER_EDIT:
                doComputerEdit(input);
                break;
            case COMPUTER_CREATE:
                doComputerCreate(input);
                break;
            default:
                break;
        }
    }

    /**
     * Action listener for index frame.
     *
     * @param input user input
     */
    private void doIndex(String[] input) {
        if (CLIOptions.QUIT.equals(input[0])) {
            this.status = CLIStatus.EXIT;
        } else if (CLIOptions.LIST_COMPUTERS.equals(input[0])) {
            callComputers(1);
        } else if (CLIOptions.LIST_COMPANIES.equals(input[0])) {
            callCompanies();
        } else if (CLIOptions.CREATE_COMPUTER.equals(input[0])) {
            callComputerCreate(new Computer());
        } else {
            callErrorInvalidInput();
        }
    }


    /**
     * Action listener for computer list frame.
     *
     * @param input user input
     */
    private void doComputers(String[] input) {
        try {
            if (CLIOptions.BACK.equals(input[0])) {
                callIndex();
            } else if (CLIOptions.SHOW.equals(input[0])) {
                if (input.length < 2) {
                    callErrorMissingParameter();
                } else if (!StringUtils.isNumeric(input[1])) {
                    callErrorInvalidParameter();
                } else {
                    int id = Integer.parseInt(input[1]);
                    Computer computer = getComputerById(id);

                    if (computer == null) {
                        callErrorInvalidParameter();
                    } else {
                        callComputer(computer);
                    }
                }
            } else if (CLIOptions.PREVIOUS_PAGE.equals(input[0])) {
                FrameComputers frame = (FrameComputers) this.frame;
                callComputers(frame.getPageNumber() - 1);
            } else if (CLIOptions.NEXT_PAGE.equals(input[0])) {
                FrameComputers frame = (FrameComputers) this.frame;
                callComputers(frame.getPageNumber() + 1);
            } else {
                callErrorInvalidInput();
            }
        } catch (QueryException | DatasourceException e) {
            callErrorInternalServerError(e);
        }
    }

    /**
     * Action listener for computer details frame.
     *
     * @param input user input
     */
    private void doComputer(String[] input) {
        if (CLIOptions.BACK.equals(input[0])) {
            callComputers(1);
        } else if (CLIOptions.DELETE.equals(input[0])) {
            try {
                deleteComputer();
                callComputers(1);
            } catch (DatasourceException | QueryException e) {
                callErrorInternalServerError(e);
            }
        } else if (CLIOptions.EDIT.equals(input[0])) {
            callComputerEdit();
        } else {
            callErrorInvalidInput();
        }
    }

    /**
     * Action listener for company list frame.
     *
     * @param input user input
     */
    private void doCompanies(String[] input) {
        try {
            if (CLIOptions.BACK.equals(input[0])) {
                callIndex();
            } else if (CLIOptions.SHOW.equals(input[0])) {
                if (input.length < 2) {
                    callErrorMissingParameter();
                } else if (!StringUtils.isNumeric(input[1])) {
                    callErrorInvalidParameter();
                } else {
                    int id = Integer.parseInt(input[1]);
                    Company company = getCompanyById(id);

                    if (company == null) {
                        callErrorInvalidParameter();
                    } else {
                        callCompany(company);
                    }
                }
            } else {
                callErrorInvalidInput();
            }
        } catch (QueryException | DatasourceException e) {
            callErrorInternalServerError(e);
        }
    }

    /**
     * Action listener for company details frame.
     *
     * @param input user input
     */
    private void doCompany(String[] input) {
        if (CLIOptions.BACK.equals(input[0])) {
            callCompanies();
        } else if (CLIOptions.DELETE.equals(input[0])) {
            try {
                deleteCompany();
                callCompanies();
            } catch (DatasourceException | QueryException e) {
                callErrorInternalServerError(e);
            }
        } else {
            callErrorInvalidInput();
        }
    }

    /**
     * Action listener for computer editing frame.
     *
     * @param input user input
     */
    private void doComputerEdit(String[] input) {
        if (CLIOptions.CANCEL.equals(input[0])) {
            callComputerBack();
        } else if (CLIOptions.SAVE.equals(input[0])) {
            try {
                Computer computer = getComputerFromPage();
                computerService.update(computer);
                callComputer(computer);
            } catch (DatasourceException | QueryException e) {
                callErrorInternalServerError(e);
            }
        } else {
            doComputerChange(input);
        }
    }

    /**
     * Action listener for computer creating frame.
     *
     * @param input user input
     */
    private void doComputerCreate(String[] input) {
        if (CLIOptions.CANCEL.equals(input[0])) {
            callIndex();
        } else if (CLIOptions.SAVE.equals(input[0])) {
            try {
                Computer computer = getComputerFromPage();
                computerService.create(computer);
                callComputer(computer);
            } catch (DatasourceException | QueryException e) {
                callErrorInternalServerError(e);
            }
        } else {
            doComputerChange(input);
        }
    }

    /**
     * Action listener for computer changing details frame.
     *
     * @param input user input
     */
    private void doComputerChange(String[] input) {
        try {
            if (CLIOptions.NEW_NAME.equals(input[0])) {
                if (input.length < 2) {
                    callErrorMissingParameter();
                } else {
                    if (ComputerValidator.validateName(input[1])) {
                        Computer computer = getComputerFromPage();
                        computer.setName(input[1]);
                    } else {
                        this.frame.setError("Incorrect name specified");
                    }
                }
            } else if (CLIOptions.NEW_INTRODUCED.equals(input[0])) {
                Computer computer = getComputerFromPage();

                if (input.length >= 2) {
                    String date = input[1];

                    if (ComputerValidator.validateIntroduced(date)) {

                        LocalDate introduced = LocalDate.parse(date);
                        computer.setIntroduced(introduced);
                    } else {
                        this.frame.setError("Incorrect introduction date specified");
                    }
                } else {
                    computer.setIntroduced(null);
                    computer.setDiscontinued(null);
                    this.frame.setError("Discontinuation date was automatically removed");
                }
            } else if (CLIOptions.NEW_DISCONTINUED.equals(input[0])) {
                Computer computer = getComputerFromPage();

                if (input.length >= 2) {
                    String date = input[1];

                    if (ComputerValidator.validateDiscontinued(date)) {
                        LocalDate discontinued = LocalDate.parse(date);
                        computer.setDiscontinued(discontinued);
                    } else {
                        this.frame.setError("Incorrect discontinuation date specified");
                    }
                } else {
                    computer.setDiscontinued(null);
                }
            } else if (CLIOptions.NEW_MANUFACTURER.equals(input[0])) {
                Company manufacturer = null;

                if (input.length >= 2) {
                    int companyId = Integer.parseInt(input[1]);
                    manufacturer = companyService.find(companyId);

                    if (manufacturer == null) {
                        this.frame.setError("No company found for this id");
                    }
                }

                Computer computer = getComputerFromPage();
                computer.setManufacturer(manufacturer);
                computerService.update(computer);
            } else {
                callErrorInvalidInput();
            }
        } catch (QueryException | DatasourceException e) {
            callErrorInternalServerError(e);
        }
    }

    /**
     * Calls index frame.
     */
    private void callIndex() {
        this.frame = new FrameBuilder().buildIndex();
        this.status = CLIStatus.INDEX;
    }

    /**
     * Calls computer list frame.
     *
     * @param offset page offset
     */
    private void callComputers(int offset) {
        try {
            Page<Computer> computers = computerService.page("", "computerName", "ASC", 10, offset);
            this.frame = new FrameBuilder().buildComputers(computers);
            this.status = CLIStatus.COMPUTERS;
        } catch (QueryException | DatasourceException e) {
            callErrorInternalServerError(e);
        }

    }

    /**
     * Calls computer details frame.
     *
     * @param computer computer to be detailed.
     */
    private void callComputer(Computer computer) {
        this.frame = new FrameBuilder().buildComputer(computer);
        this.status = CLIStatus.COMPUTER;
    }

    /**
     * Calls company list frame.
     */
    private void callCompanies() {
        try {
            List<Company> companies = companyService.list();
            this.frame = new FrameBuilder().buildCompanies(companies);
            this.status = CLIStatus.COMPANIES;
        } catch (QueryException | DatasourceException e) {
            callErrorInternalServerError(e);
        }
    }

    /**
     * Calls company details frame.
     *
     * @param company company to be detailed.
     */
    private void callCompany(Company company) {
        this.frame = new FrameBuilder().buildCompany(company);
        this.status = CLIStatus.COMPANY;
    }

    /**
     * Calls computer editing frame.
     */
    private void callComputerEdit() {
        Computer computer = getComputerFromPage();
        this.frame = new FrameBuilder().buildComputerEdit(computer);
        this.status = CLIStatus.COMPUTER_EDIT;
    }

    /**
     * Calls computer details frame back.
     */
    private void callComputerBack() {
        try {
            Computer computer = getComputerFromPage();
            computer = this.getComputerById(computer.getId());
            callComputer(computer);
        } catch (QueryException | DatasourceException e) {
            callErrorInternalServerError(e);
        }
    }

    /**
     * Calls computer creation frame.
     *
     * @param computer the computer to be created.
     */
    private void callComputerCreate(Computer computer) {
        this.frame = new FrameBuilder().buildComputerCreate(computer);
        this.status = CLIStatus.COMPUTER_CREATE;
    }

    /**
     * Calls invalid input frame component.
     */
    private void callErrorInvalidInput() {
        this.frame.setError("Invalid input");
    }

    /**
     * Calls missing parameter frame component.
     */
    private void callErrorMissingParameter() {
        this.frame.setError("Missing parameter");
    }

    /**
     * Calls invalid parameter frame component.
     */
    private void callErrorInvalidParameter() {
        this.frame.setError("Invalid parameter");
    }

    /**
     * Calls internal server error frame component.
     *
     * @param e unexpected exception
     */
    private void callErrorInternalServerError(Exception e) {
        logger.error("an unexpected error occurred", e);
        this.frame.setError("Internal server error");
    }

    /**
     * Deletes computer.
     *
     * @throws DatasourceException an unexpected error occurred
     * @throws QueryException      an unexpected error occurred
     */
    private void deleteComputer() throws DatasourceException, QueryException {
        Computer computer = getComputerFromPage();
        computerService.delete(computer);
    }

    /**
     * Deletes company.
     *
     * @throws DatasourceException an unexpected error occurred
     * @throws QueryException      an unexpected error occurred
     */
    private void deleteCompany() throws DatasourceException, QueryException {
        Company company = getCompanyFromPage();
        companyService.delete(company);
    }

    /**
     * Gets computer by id.
     *
     * @param id computer's id
     * @return object computer
     * @throws QueryException      an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    private Computer getComputerById(int id) throws QueryException, DatasourceException {
        return computerService.find(id);
    }

    /**
     * Gets company by id.
     *
     * @param id company's id
     * @return object company
     * @throws QueryException      an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    private Company getCompanyById(int id) throws QueryException, DatasourceException {
        return companyService.find(id);
    }

    /**
     * Gets computers held by last frame.
     *
     * @return object computer
     */
    private Computer getComputerFromPage() {
        if (this.frame instanceof FrameComputer) {
            FrameComputer frame = (FrameComputer) this.frame;
            return frame.getComputer();
        } else {
            FrameComputerChange frame = (FrameComputerChange) this.frame;
            return frame.getComputer();
        }
    }

    /**
     * Gets company held by last frame.
     *
     * @return object company
     */
    private Company getCompanyFromPage() {
        FrameCompany frame = (FrameCompany) this.frame;
        return frame.getCompany();
    }

}
