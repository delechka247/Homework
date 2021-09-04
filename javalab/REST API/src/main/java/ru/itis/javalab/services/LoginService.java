package ru.itis.javalab.services;

import ru.itis.javalab.dto.EmailPasswordDto;
import ru.itis.javalab.dto.JwtDto;


public interface LoginService {
    JwtDto login(EmailPasswordDto emailPassword);
}
