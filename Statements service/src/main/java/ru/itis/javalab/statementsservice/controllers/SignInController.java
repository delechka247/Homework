package ru.itis.javalab.statementsservice.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.statementsservice.dto.EmailPasswordDto;
import ru.itis.javalab.statementsservice.dto.JwtDto;
import ru.itis.javalab.statementsservice.dto.TokensDto;
import ru.itis.javalab.statementsservice.services.SignInService;


@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/signIn")
    @ApiOperation(value = "Вход")
    public ResponseEntity<TokensDto> login(@RequestBody EmailPasswordDto emailPassword) {
        return ResponseEntity.ok(signInService.signIn(emailPassword));
    }

}
