package ru.itis.javalab.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.dto.SignInForm;
import ru.itis.javalab.services.UsersService;

@Controller
public class SignInController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String getSignInPage(Model model) {
        model.addAttribute("userForm", new SignInForm());
        return "sign_in";
    }

}
