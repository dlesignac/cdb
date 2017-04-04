package fr.ebiz.cdb.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * @param model model
     * @return String
     */
    @GetMapping
    public String getLogin(ModelMap model) {
        return "common/login";
    }

}
