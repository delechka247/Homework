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
import ru.kpfu.itis.transportsem.models.User;
import ru.kpfu.itis.transportsem.repositories.UsersRepository;

import java.util.function.Supplier;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public JwtDto signIn(EmailPasswordDto emailPassword) {
        User user = usersRepository.findByEmail(emailPassword.getEmail())
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(emailPassword.getPassword(), user.getHashPassword())) {

            String value = JWT.create()
                    .withSubject(user.getId().toString())
                    .withClaim("first name", user.getFirstName())
                    .withClaim("last name", user.getLastName())
                    .withClaim("role", user.getRole().toString())
                    .withClaim("state", user.getState().toString())
                    .withClaim("email", user.getEmail())
                    .sign(Algorithm.HMAC256("S1e2C3r4E5t6"));

            return JwtDto.builder().value(value).build();

        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}