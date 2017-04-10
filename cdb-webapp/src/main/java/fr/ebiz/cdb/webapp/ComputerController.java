package fr.ebiz.cdb.webapp;

import fr.ebiz.cdb.binding.ComputerDTO;
import fr.ebiz.cdb.binding.ComputerDeleteRequest;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/computer")
public class ComputerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

    private static final String ATTRIBUTE_COMPANIES = "companies";
    private static final String ATTRIBUTE_COMPUTER = "computer";
    private static final String ATTRIBUTE_SUCCESS = "success";

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
        model.addAttribute(ATTRIBUTE_COMPANIES, companyService.list());
        return "addComputer";
    }

    /**
     * Post add.
     *
     * @param computerDTO   computerDTO
     * @param bindingResult bindingResult
     * @param model         model
     * @return add
     */
    @PostMapping("/add")
    public String postAdd(
            @Valid ComputerDTO computerDTO,
            BindingResult bindingResult,
            ModelMap model
    ) {
        if (!bindingResult.hasErrors()) {
            computerService.create(computerDTO);
            model.addAttribute(ATTRIBUTE_SUCCESS, true);
        } else {
            model.addAttribute(ATTRIBUTE_SUCCESS, false);
        }

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
        model.addAttribute(ATTRIBUTE_COMPUTER, computerService.find(id));
        model.addAttribute(ATTRIBUTE_COMPANIES, companyService.list());
        return "editComputer";
    }

    /**
     * Post edit.
     *
     * @param id            id
     * @param computerDTO   computerDTO
     * @param bindingResult bindingResult
     * @param model         model
     * @return edit
     */
    @PostMapping("/{id}")
    public String postEdit(
            @PathVariable Integer id,
            @Valid ComputerDTO computerDTO,
            BindingResult bindingResult,
            ModelMap model
    ) {
        if (!bindingResult.hasErrors()) {
            computerService.update(computerDTO);
            model.addAttribute(ATTRIBUTE_SUCCESS, true);
        } else {
            model.addAttribute(ATTRIBUTE_SUCCESS, false);
        }

        return getEdit(model, id);
    }

    /**
     * Post dashboard.
     *
     * @param computerDeleteRequest computerDeleteRequest
     * @param bindingResult         bindingResult
     * @param redirectAttributes    redirectAttributes
     * @return dashboard
     */
    @PostMapping("/delete")
    public String postDelete(
            @Valid ComputerDeleteRequest computerDeleteRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!bindingResult.hasErrors()) {
            computerService.deleteMany(computerDeleteRequest);
            redirectAttributes.addFlashAttribute(ATTRIBUTE_SUCCESS, true);
        } else {
            redirectAttributes.addFlashAttribute(ATTRIBUTE_SUCCESS, false);
        }

        return "redirect:/dashboard";
    }

}