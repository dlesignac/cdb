package fr.ebiz.cdb.api;

import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyRestController {

    @Autowired
    private ICompanyService companyService;

    /**
     * Get computer page.
     *
     * @return List<Company>
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Company> fetch() {
        return companyService.list();
    }
}
