package ru.itis.javalab.statementsservice.services;

import ru.itis.javalab.statementsservice.dto.TokensDto;
import ru.itis.javalab.statementsservice.models.User;

public interface RefreshTokenService {
    boolean accessCheck(String accessTokenValue);
    boolean refreshCheck(String refreshTokenValue);
    TokensDto getNewTokens(Long id, String refreshTokenValue);
    TokensDto generateTokens(User user);
}
