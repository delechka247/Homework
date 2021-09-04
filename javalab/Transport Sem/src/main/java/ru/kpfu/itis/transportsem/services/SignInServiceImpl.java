package ru.kpfu.itis.transportsem.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportsem.dto.EmailPasswordDto;
import ru.kpfu.itis.transportsem.dto.JwtDto;
import ru.kpfu.itis.transportsem.dto.TokensDto;
import ru.kpfu.itis.transportsem.models.RefreshToken;
import ru.kpfu.itis.transportsem.models.User;
import ru.kpfu.itis.transportsem.redis.services.RedisUsersService;
import ru.kpfu.itis.transportsem.repositories.RefreshTokenRepository;
import ru.kpfu.itis.transportsem.repositories.UsersRepository;
import ru.kpfu.itis.transportsem.security.jwt.utils.TokensCreator;

import java.util.Date;
import java.util.function.Supplier;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisUsersService redisUsersService;

    @Autowired
    private RefreshTokenService refreshTokenService;


    @SneakyThrows
    @Override
    public TokensDto signIn(EmailPasswordDto emailPassword) {
        User user = usersRepository.findByEmail(emailPassword.getEmail())
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(emailPassword.getPassword(), user.getHashPassword())) {

            return refreshTokenService.generateTokens(user);

        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}