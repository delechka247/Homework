package ru.itis.javalab.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.UsersService;

import java.util.UUID;

@Controller
public class ProfileController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }
}
