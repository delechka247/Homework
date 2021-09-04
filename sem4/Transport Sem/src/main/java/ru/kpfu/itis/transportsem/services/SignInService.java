package ru.kpfu.itis.transportsem.services;

import ru.kpfu.itis.transportsem.dto.EmailPasswordDto;
import ru.kpfu.itis.transportsem.dto.JwtDto;

public interface SignInService {
    JwtDto signIn(EmailPasswordDto emailPassword);
}
