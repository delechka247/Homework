package ru.kpfu.itis.transportsem.security.details;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.transportsem.dto.UserDto;
import ru.kpfu.itis.transportsem.models.User;
import ru.kpfu.itis.transportsem.security.jwt.utils.JwtDecoder;

@Slf4j
@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        return new UserDetailsImpl(jwtDecoder.getUserFromJwt(token));
    }
}