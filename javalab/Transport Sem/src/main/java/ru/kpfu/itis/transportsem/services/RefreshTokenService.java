package ru.kpfu.itis.transportsem.services;

import ru.kpfu.itis.transportsem.dto.TokensDto;
import ru.kpfu.itis.transportsem.models.User;

public interface RefreshTokenService {
    boolean accessCheck(String accessTokenValue);
    boolean refreshCheck(String refreshTokenValue);
    TokensDto getNewTokens(Long id, String refreshTokenValue);
    TokensDto generateTokens(User user);
}
