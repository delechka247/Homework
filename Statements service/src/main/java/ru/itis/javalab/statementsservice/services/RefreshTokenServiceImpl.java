package ru.itis.javalab.statementsservice.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.javalab.statementsservice.dto.TokensDto;
import ru.itis.javalab.statementsservice.models.RefreshToken;
import ru.itis.javalab.statementsservice.models.User;
import ru.itis.javalab.statementsservice.repositories.RefreshTokenRepository;
import ru.itis.javalab.statementsservice.repositories.UsersRepository;
import ru.itis.javalab.statementsservice.security.jwt.utils.JwtDecoder;
import ru.itis.javalab.statementsservice.security.jwt.utils.TokensCreator;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class RefreshTokenServiceImpl implements ru.itis.javalab.statementsservice.services.RefreshTokenService {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private TokensCreator tokensCreator;

    @Override
    public boolean accessCheck(String accessTokenValue) {
        return jwtDecoder.getDecodedJwt(accessTokenValue).getClaim("expiration date").asDate().after(new Date());
    }

    @Override
    public boolean refreshCheck(String refreshTokenValue) {
            DecodedJWT decodedJWT = jwtDecoder.getDecodedJwt(refreshTokenValue);
            return decodedJWT.getClaim("expiration date").asDate().after(new Date());
    }

    @Override
    @Transactional
    public TokensDto getNewTokens(Long id, String refreshTokenValue) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));

        refreshTokenRepository.deleteByValue(refreshTokenValue);
        return generateTokens(user);
    }

    public TokensDto generateTokens(User user) {
        String newAccessToken = tokensCreator.createAccessToken(user);
        String newRefreshToken = tokensCreator.createRefreshToken(user);

        refreshTokenRepository.save(RefreshToken.builder()
                .value(newRefreshToken)
                .user(user)
                .build());

        TokensDto tokensDto = TokensDto.builder()
                .accessTokenDto(newAccessToken)
                .refreshTokenDto(newRefreshToken)
                .build();

        System.out.println("Новые токены: " + tokensDto);

        return tokensDto;
    }
}
