package ru.itis.javalab.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.UsersService;

import java.util.UUID;

@Controller
public class ConfirmController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/confirm/{confirm-code}", method = RequestMethod.GET)
    public String getUser(@PathVariable("confirm-code") UUID confirmCode, Model model) {
        usersService.confirmEmail(confirmCode);
        return "confirm_page";
    }

}
