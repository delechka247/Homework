package ru.kpfu.itis.transportsem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.transportsem.dto.UserDto;
import ru.kpfu.itis.transportsem.services.UsersService;

@RestController
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/profile")
    public UserDto getProfilePage(@RequestHeader("TOKEN") String token) {
        return usersService.getUserFromJwt(token);
    }

}
