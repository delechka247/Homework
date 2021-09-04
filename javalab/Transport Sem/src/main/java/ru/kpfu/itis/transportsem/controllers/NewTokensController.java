package ru.kpfu.itis.transportsem.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.transportsem.models.User;
import ru.kpfu.itis.transportsem.repositories.UsersRepository;
import ru.kpfu.itis.transportsem.security.jwt.utils.JwtDecoder;
import ru.kpfu.itis.transportsem.services.RefreshTokenService;

@RestController
public class NewTokensController {
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtDecoder jwtDecoder;

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("REFRESH-TOKEN") String refreshToken) {
        if (refreshToken != null) {
            DecodedJWT decodedJWT = jwtDecoder.getDecodedJwt(refreshToken);
            String id = decodedJWT.getSubject();
            return ResponseEntity.ok(refreshTokenService.getNewTokens(Long.parseLong(id), refreshToken));
        } else {
            return ResponseEntity.ok("Bad refresh token");
        }
    }

}
