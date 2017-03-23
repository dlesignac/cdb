package fr.ebiz.cdb.web.controller;

import fr.ebiz.cdb.core.dto.ComputerPageRequest;
import fr.ebiz.cdb.core.service.IComputerService;
import fr.ebiz.cdb.core.validator.PageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    private static final String ATTRIBUTE_PAGE = "page";
    private static final String ATTRIBUTE_SUCCESS = "success";
    private static final String ATTRIBUTE_EXCEPTION = "exception";

    private static final String DELETE_SEPARATOR = ",";

    @Autowired
    private IComputerService computerService;

    /**
     * Get dashboard.
     *
     * @param computerPageRequest computerPageRequest
     * @param bindingResult       bindingResult
     * @param model               model
     * @return dashboard
     */
    @GetMapping({"", "dashboard"})
    public String getDashboard(
            @Valid ComputerPageRequest computerPageRequest,
            BindingResult bindingResult,
            ModelMap model
    ) {
        PageValidator.validate(computerPageRequest);
        model.addAttribute(ATTRIBUTE_PAGE, computerService.page(computerPageRequest));
        return "dashboard";
    }

}
