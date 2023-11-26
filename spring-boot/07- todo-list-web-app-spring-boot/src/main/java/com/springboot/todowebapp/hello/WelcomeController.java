package com.springboot.todowebapp.hello;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

//    private AuthenticationService authService;

//    public LoginController(AuthenticationService authService) {
//        this.authService = authService;
//    }

//    private Logger logger = LoggerFactory.getLogger(getClass());

//    @RequestMapping("login")
//    public String login(@RequestParam String name, ModelMap model) {
//        model.put("name", name);
//        logger.info("Request parameter name is {}", name);
//        return "login";
//    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomeView(ModelMap model) {
        model.put("name", getLoggedInUsername());
        return "welcome";
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String redirectToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
//        // Authentication
//        // name = admin
//        // password = 1234
//        if (authService.authenticate(name, password)) {
//            model.put("name", name);
//            return "welcome";
//        } else {
//            model.put("errorMessage", "Invalid Credentials");
//            return "login";
//        }
//    }
}
