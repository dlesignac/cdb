package fr.ebiz.cdb.console;

import fr.ebiz.cdb.CLIConfig;
import fr.ebiz.cdb.binding.ComputerDTO;
import fr.ebiz.cdb.binding.ComputerDTOMapper;
import fr.ebiz.cdb.binding.ComputerPageRequest;
import fr.ebiz.cdb.console.frame.Frame;
import fr.ebiz.cdb.console.frame.FrameBuilder;
import fr.ebiz.cdb.console.frame.FrameCompany;
import fr.ebiz.cdb.console.frame.FrameComputer;
import fr.ebiz.cdb.console.frame.FrameComputerChange;
import fr.ebiz.cdb.console.frame.FrameComputers;
import fr.ebiz.cdb.core.Column;
import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.core.Computer;
import fr.ebiz.cdb.core.Order;
import fr.ebiz.cdb.core.Page;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.IComputerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CLI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CLI.class);

    /**
     * CLI entry point.
     *
     * @param args entry point arguments
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(CLIConfig.class);
        CLI cli = ctx.getBean(CLI.class);
        cli.loop();
    }

    private final ICompanyService companyService;

    private final IComputerService computerService;

    private Scanner in = new Scanner(System.in);
    private Frame frame = new FrameBuilder().buildIndex();
    private CLIStatus status = CLIStatus.INDEX;

    /**
     * @param companyService  companyService
     * @param computerService computerService
     */
    @Autowired
    public CLI(ICompanyService companyService, IComputerService computerService) {
        this.companyService = companyService;
        this.computerService = computerService;
    }

    /**
     * Main loop. Prints pages and waits for user inputs.
     */
    private void loop() {
        while (status != CLIStatus.EXIT) {
            frame.display();

            try {
                listen();
            } catch (Exception e) {
                frame.setError("An error occurred");
                LOGGER.error("caught exception while running CLI", e);
            }
        }

        this.in.close();
    }

    /**
     * Waits for and manages user inputs.
     */
    private void listen() {
        String[] input = in.nextLine().split(" ");

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
            status = CLIStatus.EXIT;
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
            deleteComputer();
            callComputers(1);
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
            deleteCompany();
            callCompanies();

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

            Computer computer = getComputerFromPage();
            computerService.update(computer);
            callComputer(computer);
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
            Computer computer = getComputerFromPage();
            computerService.create(computer);
            callComputer(computer);

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
        ComputerDTO computerDTO = getComputerDTOFromPage();

        if (CLIOptions.NEW_NAME.equals(input[0])) {
            computerDTO.setName(input[1]);
        } else if (CLIOptions.NEW_INTRODUCED.equals(input[0])) {
            computerDTO.setIntroduced(input[1]);
        } else if (CLIOptions.NEW_DISCONTINUED.equals(input[0])) {
            computerDTO.setDiscontinued(input[1]);
        } else if (CLIOptions.NEW_MANUFACTURER.equals(input[0])) {
            computerDTO.setCompanyId(Integer.parseInt(input[1]));
        } else if (CLIOptions.SAVE.equals(input[0])) {
            Computer computer = ComputerDTOMapper.mapFromDTO(computerDTO);
            computerService.update(computer);
        } else if (CLIOptions.BACK.equals(input[0])) {
            callComputers(0);
        } else {
            callErrorInvalidInput();
        }
    }

    /**
     * Calls index frame.
     */
    private void callIndex() {
        frame = new FrameBuilder().buildIndex();
        status = CLIStatus.INDEX;
    }

    /**
     * Calls computer list frame.
     *
     * @param offset page offset
     */
    private void callComputers(int offset) {
        ComputerPageRequest dto = new ComputerPageRequest();
        dto.setFilter("");
        dto.setSort(Column.COMPUTER_NAME);
        dto.setOrder(Order.ASC);
        dto.setLimit(10);
        dto.setPage(offset);

        Page<Computer> computers = computerService.page(dto);
        frame = new FrameBuilder().buildComputers(computers);
        status = CLIStatus.COMPUTERS;
    }

    /**
     * Calls computer details frame.
     *
     * @param computer computer to be detailed.
     */
    private void callComputer(Computer computer) {
        frame = new FrameBuilder().buildComputer(computer);
        status = CLIStatus.COMPUTER;
    }

    /**
     * Calls company list frame.
     */
    private void callCompanies() {
        List<Company> companies = companyService.list();
        frame = new FrameBuilder().buildCompanies(companies);
        status = CLIStatus.COMPANIES;
    }

    /**
     * Calls company details frame.
     *
     * @param company company to be detailed.
     */
    private void callCompany(Company company) {
        frame = new FrameBuilder().buildCompany(company);
        status = CLIStatus.COMPANY;
    }

    /**
     * Calls computer editing frame.
     */
    private void callComputerEdit() {
        Computer computer = getComputerFromPage();
        frame = new FrameBuilder().buildComputerEdit(computer);
        status = CLIStatus.COMPUTER_EDIT;
    }

    /**
     * Calls computer details frame back.
     */
    private void callComputerBack() {
        Computer computer = getComputerFromPage();
        computer = getComputerById(computer.getId());
        callComputer(computer);
    }

    /**
     * Calls computer creation frame.
     *
     * @param computer the computer to be created.
     */
    private void callComputerCreate(Computer computer) {
        frame = new FrameBuilder().buildComputerCreate(computer);
        status = CLIStatus.COMPUTER_CREATE;
    }

    /**
     * Calls invalid input frame component.
     */
    private void callErrorInvalidInput() {
        frame.setError("Invalid input");
    }

    /**
     * Calls missing parameter frame component.
     */
    private void callErrorMissingParameter() {
        frame.setError("Missing parameter");
    }

    /**
     * Calls invalid parameter frame component.
     */
    private void callErrorInvalidParameter() {
        frame.setError("Invalid parameter");
    }

    /**
     * Deletes computer.
     */
    private void deleteComputer() {
        Computer computer = getComputerFromPage();
        computerService.delete(computer);
    }

    /**
     * Deletes company.
     */
    private void deleteCompany() {
        Company company = getCompanyFromPage();
        companyService.delete(company);
    }

    /**
     * Gets computer by id.
     *
     * @param id computer's id
     * @return object computer
     */
    private Computer getComputerById(int id) {
        return computerService.find(id);
    }

    /**
     * Gets company by id.
     *
     * @param id company's id
     * @return object company
     */
    private Company getCompanyById(int id) {
        return companyService.find(id);
    }

    /**
     * Gets computers held by last frame.
     *
     * @return object computer
     */
    private Computer getComputerFromPage() {
        FrameComputer frame = (FrameComputer) this.frame;
        return frame.getComputer();
    }

    /**
     * Gets computers held by last frame.
     *
     * @return object computerDTO
     */
    private ComputerDTO getComputerDTOFromPage() {
        FrameComputerChange frame = (FrameComputerChange) this.frame;
        return frame.getComputerDTO();
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