package com.easework.simuapi.simu_api.controller.uiController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class HomepageController {

    @GetMapping("/")
    public RedirectView redirectHomePage(Model model) {
        return new RedirectView("/simuapi");
    }

    @GetMapping("/simuapi")
    public String viewHomePage(Model model) {
        model.addAttribute("allemplist", "Hellowworld");
        return "index.html";
    }

}
