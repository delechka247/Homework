package ru.kpfu.itis.transportsem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.transportsem.dto.ReservationDto;
import ru.kpfu.itis.transportsem.dto.UserDto;
import ru.kpfu.itis.transportsem.services.ReservationsService;
import ru.kpfu.itis.transportsem.services.UsersService;

import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ReservationsService reservationsService;

    @GetMapping("/profile")
    @ApiOperation(value = "Получение информации о пользователе")
    public UserDto getProfilePage(@RequestHeader("TOKEN") String token) {
        return usersService.getUserFromJwt(token);
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Получение броней пользователя")
    @GetMapping("/profile/reservation")
    public List<ReservationDto> getUsersReservations(@RequestHeader("TOKEN") String token) {
        return reservationsService.getUsersReservations(token);
    }
}
