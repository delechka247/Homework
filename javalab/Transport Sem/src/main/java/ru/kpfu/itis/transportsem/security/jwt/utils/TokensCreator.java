package ru.kpfu.itis.transportsem.security.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.transportsem.models.User;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@Component
public class TokensCreator {

    public String createAccessToken(User user) {
        Date date = new Date();
        date.setTime(date.getTime() + 9000000l);

        String newAccessToken = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("first name", user.getFirstName())
                .withClaim("last name", user.getLastName())
                .withClaim("role", user.getRole().toString())
                .withClaim("state", user.getState().toString())
                .withClaim("email", user.getEmail())
                .withClaim("expiration date", date)
                .sign(Algorithm.HMAC256("S1e2C3r4E5t6"));
        return newAccessToken;
    }

    public String createRefreshToken(User user) {

        Date date = new Date();
        date.setTime(date.getTime() + 9072000000l);

        String newRefreshToken = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("expiration date", date)
                .sign(Algorithm.HMAC256("S1e2C3r4E5t6"));

        return newRefreshToken;
    }
}
