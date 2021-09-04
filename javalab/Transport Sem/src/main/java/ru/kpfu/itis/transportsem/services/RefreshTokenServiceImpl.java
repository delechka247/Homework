package ru.kpfu.itis.transportsem.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportsem.dto.TokensDto;
import ru.kpfu.itis.transportsem.models.RefreshToken;
import ru.kpfu.itis.transportsem.redis.services.RedisUsersService;
import ru.kpfu.itis.transportsem.repositories.BlacklistRepository;
import ru.kpfu.itis.transportsem.repositories.RefreshTokenRepository;
import ru.kpfu.itis.transportsem.repositories.UsersRepository;
import ru.kpfu.itis.transportsem.security.jwt.utils.JwtDecoder;
import ru.kpfu.itis.transportsem.models.User;
import ru.kpfu.itis.transportsem.security.jwt.utils.TokensCreator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RedisUsersService redisUsersService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private TokensCreator tokensCreator;

    @Autowired
    private BlacklistRepository blacklistRepository;

    @Override
    public boolean accessCheck(String accessTokenValue) {
        return jwtDecoder.getDecodedJwt(accessTokenValue).getClaim("expiration date").asDate().after(new Date());
    }

    @Override
    public boolean refreshCheck(String refreshTokenValue) {
            DecodedJWT decodedJWT = jwtDecoder.getDecodedJwt(refreshTokenValue);
            User user = usersRepository.findById(Long.parseLong(decodedJWT.getSubject()))
                    .orElseThrow(IllegalArgumentException::new);
            if (!redisUsersService.checkToken(refreshTokenValue, user)) {
                return false;
            }
            return decodedJWT.getClaim("expiration date").asDate().after(new Date());
    }

    @Override
    @Transactional
    public TokensDto getNewTokens(Long id, String refreshTokenValue) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));

        refreshTokenRepository.deleteByValue(refreshTokenValue);
        blacklistRepository.save(refreshTokenValue);
        return generateTokens(user);
    }

    public TokensDto generateTokens(User user) {
        String newAccessToken = tokensCreator.createAccessToken(user);
        String newRefreshToken = tokensCreator.createRefreshToken(user);

        refreshTokenRepository.save(RefreshToken.builder()
                .value(newRefreshToken)
                .user(user)
                .build());

        redisUsersService.addTokenToUser(user, newRefreshToken);

        TokensDto tokensDto = TokensDto.builder()
                .accessTokenDto(newAccessToken)
                .refreshTokenDto(newRefreshToken)
                .build();

        System.out.println("Новые токены: " + tokensDto);

        return tokensDto;
    }
}
