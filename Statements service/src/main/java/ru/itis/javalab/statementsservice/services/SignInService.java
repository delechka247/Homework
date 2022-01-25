package ru.itis.javalab.statementsservice.services;

import ru.itis.javalab.statementsservice.dto.EmailPasswordDto;
import ru.itis.javalab.statementsservice.dto.TokensDto;

public interface SignInService {
    TokensDto signIn(EmailPasswordDto emailPassword);
}
