package ru.kpfu.itis.transportsem.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ru.kpfu.itis.transportsem.security.jwt.JwtAuthentication jwtAuthentication = (ru.kpfu.itis.transportsem.security.jwt.JwtAuthentication)authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        jwtAuthentication.setAuthenticated(true);
        jwtAuthentication.setUserDetails(userDetails);
        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ru.kpfu.itis.transportsem.security.jwt.JwtAuthentication.class.equals(authentication);
    }
}
