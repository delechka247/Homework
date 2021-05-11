package ru.kpfu.itis.transportsem.security.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.transportsem.dto.UserDto;
import ru.kpfu.itis.transportsem.models.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class JwtDecoder {

    private String token;

    public User getUserFromJwt(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("S1e2C3r4E5t6"))
                .build()
                .verify(token);

        User user = User.builder()
                .id(Long.valueOf(decodedJWT.getSubject()))
                .email(decodedJWT.getClaim("email").toString().replaceAll("\"", ""))
                .firstName(decodedJWT.getClaim("first name").toString().replaceAll("\"", ""))
                .lastName(decodedJWT.getClaim("last name").toString().replaceAll("\"", ""))
                .role(User.Role.valueOf(decodedJWT.getClaim("role").toString().replaceAll("\"", "")))
                .state(User.State.valueOf(decodedJWT.getClaim("state").toString().replaceAll("\"", "")))
                .build();

        return user;
    }
}
