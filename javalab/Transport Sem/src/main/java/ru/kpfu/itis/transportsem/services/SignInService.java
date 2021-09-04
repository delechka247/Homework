package ru.kpfu.itis.transportsem.services;

import ru.kpfu.itis.transportsem.dto.EmailPasswordDto;
import ru.kpfu.itis.transportsem.dto.JwtDto;
import ru.kpfu.itis.transportsem.dto.TokensDto;

public interface SignInService {
    TokensDto signIn(EmailPasswordDto emailPassword);
}
