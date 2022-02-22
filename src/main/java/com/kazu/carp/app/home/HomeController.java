package com.kazu.carp.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author akifova
 * 22.03.2021
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String main(Model model) {
        return "ui/index";
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String apiInfo(Model model) {
        return "redirect:/swagger-ui.html";
    }
}