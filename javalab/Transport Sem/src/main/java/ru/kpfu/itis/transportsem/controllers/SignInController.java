package ru.kpfu.itis.transportsem.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.transportsem.dto.EmailPasswordDto;
import ru.kpfu.itis.transportsem.dto.JwtDto;
import ru.kpfu.itis.transportsem.dto.TokensDto;
import ru.kpfu.itis.transportsem.models.User;
import ru.kpfu.itis.transportsem.services.SignInService;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/signIn")
    @ApiOperation(value = "Вход")
    public ResponseEntity<TokensDto> login(@RequestBody EmailPasswordDto emailPassword) {
        System.out.println("H");
        return ResponseEntity.ok(signInService.signIn(emailPassword));
    }

}

