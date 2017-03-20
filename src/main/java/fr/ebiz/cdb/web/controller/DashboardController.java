package fr.ebiz.cdb.web.controller;

import fr.ebiz.cdb.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.model.Column;
import fr.ebiz.cdb.model.Order;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.TransactionFailedException;
import fr.ebiz.cdb.validator.ComputerDeletionValidator;
import fr.ebiz.cdb.validator.PageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    private static final String ATTRIBUTE_PAGE = "page";
    private static final String ATTRIBUTE_ERRORS = "errors";
    private static final String ATTRIBUTE_EXCEPTION = "exception";

    private static final String DELETE_SEPARATOR = ",";

    @Autowired
    private IComputerService computerService;

    /**
     * Get dashboard.
     *
     * @param model  model
     * @param filter filter
     * @param sort   sort
     * @param order  order
     * @param limit  limit
     * @param number number
     * @return dashboard
     */
    @GetMapping({"", "dashboard"})
    public String getDashboard(
            ModelMap model,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "order", required = false) String order,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "page", required = false) Integer number
    ) {
        ComputerPageDTO pageRequest = new ComputerPageDTO();
        pageRequest.setFilter(filter);
        pageRequest.setSort(Column.of(sort));
        pageRequest.setOrder(Order.of(order));
        pageRequest.setLimit(limit);
        pageRequest.setNumber(number);

        PageValidator.validate(pageRequest);

        try {
            model.addAttribute(ATTRIBUTE_PAGE, computerService.page(pageRequest));
        } catch (TransactionFailedException e) {
            LOGGER.error("failed to get page", e);
            model.addAttribute(ATTRIBUTE_EXCEPTION, e);
            return "500";
        }

        return "dashboard";
    }

    /**
     * Post dashboard.
     *
     * @param model     model
     * @param selection selection
     * @return dashboard
     */
    @PostMapping("/delete-computers")
    public String postDelete(
            ModelMap model,
            @RequestParam(value = "selection") String selection
    ) {
        String[] delete = selection.split(DELETE_SEPARATOR);
        List<String> ids = new ArrayList<>();
        Collections.addAll(ids, delete);
        ComputerDeletionDTO computerDeletionDTO = new ComputerDeletionDTO(ids);

        List<String> errors = ComputerDeletionValidator.validate(computerDeletionDTO);

        if (errors.isEmpty()) {
            try {
                computerService.deleteMany(computerDeletionDTO);
            } catch (TransactionFailedException e) {
                LOGGER.error("failed to delete computers", e);
                errors.add(e.getMessage());
            }
        }

        model.addAttribute(ATTRIBUTE_ERRORS, errors);
        return getDashboard(model, null, null, null, null, null);
    }

}
