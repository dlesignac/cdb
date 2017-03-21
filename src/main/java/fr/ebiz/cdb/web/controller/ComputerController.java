package fr.ebiz.cdb.web.controller;

import fr.ebiz.cdb.core.dto.ComputerDTO;
import fr.ebiz.cdb.core.dto.ComputerDTOMapper;
import fr.ebiz.cdb.core.model.Computer;
import fr.ebiz.cdb.core.service.ICompanyService;
import fr.ebiz.cdb.core.service.IComputerService;
import fr.ebiz.cdb.core.service.exception.TransactionFailedException;
import fr.ebiz.cdb.core.validator.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/computer")
public class ComputerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

    private static final String ATTRIBUTE_COMPANIES = "companies";
    private static final String ATTRIBUTE_COMPUTER = "computer";
    private static final String ATTRIBUTE_ERRORS = "errors";
    private static final String ATTRIBUTE_EXCEPTION = "exception";

    @Autowired
    private IComputerService computerService;

    @Autowired
    private ICompanyService companyService;

    /**
     * Get add.
     *
     * @param model model
     * @return add
     */
    @GetMapping("/add")
    public String getAdd(ModelMap model) {
        try {
            model.addAttribute(ATTRIBUTE_COMPANIES, companyService.list());
            return "addComputer";
        } catch (TransactionFailedException e) {
            LOGGER.error("failed to load companies", e);
            model.addAttribute(ATTRIBUTE_EXCEPTION, e);
            return "500";
        }
    }

    /**
     * Post add.
     *
     * @param model        model
     * @param name         name
     * @param introduced   introduced
     * @param discontinued discontinued
     * @param companyId    companyId
     * @return add
     */
    @PostMapping("/add")
    public String postAdd(
            ModelMap model,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "introduced") String introduced,
            @RequestParam(value = "discontinued") String discontinued,
            @RequestParam(value = "companyId") Integer companyId
    ) {
        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setName(name);
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);
        computerDTO.setCompanyId(companyId);

        List<String> errors = ComputerValidator.validate(computerDTO);

        if (errors.isEmpty()) {
            Computer computer = ComputerDTOMapper.mapFromDTO(computerDTO);

            try {
                computerService.create(computer);
            } catch (TransactionFailedException e) {
                LOGGER.error("failed to add computer", e);
                errors.add(e.getMessage());
            }
        }

        model.addAttribute(ATTRIBUTE_ERRORS, errors);
        return getAdd(model);
    }

    /**
     * Get edit.
     *
     * @param model model
     * @param id    id
     * @return edit
     */
    @GetMapping("/{id}")
    public String getEdit(ModelMap model, @PathVariable Integer id) {
        try {
            model.addAttribute(ATTRIBUTE_COMPUTER, computerService.find(id));
            model.addAttribute(ATTRIBUTE_COMPANIES, companyService.list());
            return "editComputer";
        } catch (TransactionFailedException e) {
            LOGGER.error("failed to load companies", e);
            model.addAttribute(ATTRIBUTE_EXCEPTION, e);
            return "500";
        }
    }

    /**
     * Post edit.
     *
     * @param model        model
     * @param id           id
     * @param name         name
     * @param introduced   introduced
     * @param discontinued discontinued
     * @param companyId    companyId
     * @return edit
     */
    @PostMapping("/{id}")
    public String postEdit(
            ModelMap model,
            @PathVariable Integer id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "introduced") String introduced,
            @RequestParam(value = "discontinued") String discontinued,
            @RequestParam(value = "companyId") Integer companyId
    ) {
        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setName(name);
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);
        computerDTO.setCompanyId(companyId);

        List<String> errors = ComputerValidator.validate(computerDTO);

        if (errors.isEmpty()) {
            Computer computer = ComputerDTOMapper.mapFromDTO(computerDTO);

            try {
                computerService.update(computer);
            } catch (TransactionFailedException e) {
                LOGGER.error("failed to update computer", e);
                errors.add(e.getMessage());
            }
        }

        model.addAttribute(ATTRIBUTE_ERRORS, errors);
        return getEdit(model, id);
    }

}