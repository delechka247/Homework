package ru.itis.javalab.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.services.UsedMethodsService;

@Controller
public class StatisticController {

    @Autowired
    UsedMethodsService usedMethodsService;

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public String getStatisticPage(Model model) {

        model.addAttribute("methods", usedMethodsService.countMethods());

        return "statistic_page";
    }
}
