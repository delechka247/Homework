package ru.itis.javalab.statementsservice.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.statementsservice.dto.EmailPasswordDto;
import ru.itis.javalab.statementsservice.dto.TokensDto;
import ru.itis.javalab.statementsservice.models.User;
import ru.itis.javalab.statementsservice.redis.services.RedisUsersService;
import ru.itis.javalab.statementsservice.repositories.UsersRepository;

import java.util.function.Supplier;

@Service
public class SignInServiceImpl implements ru.itis.javalab.statementsservice.services.SignInService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private RedisUsersService redisUsersService;

    @SneakyThrows
    @Override
    public TokensDto signIn(EmailPasswordDto emailPassword) {
        User user = usersRepository.findByEmail(emailPassword.getEmail())
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(emailPassword.getPassword(), user.getHashPassword())) {
            TokensDto tokensDto = refreshTokenService.generateTokens(user);
            redisUsersService.addTokenToUser(user, tokensDto.getAccessTokenDto());
            return tokensDto;

        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}