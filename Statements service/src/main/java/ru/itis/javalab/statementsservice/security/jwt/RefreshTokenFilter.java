package ru.itis.javalab.statementsservice.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.javalab.statementsservice.services.RefreshTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RefreshTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String refreshTokenValue = request.getHeader("REFRESH-TOKEN");
        if (refreshTokenValue != null) {
            if (!refreshTokenService.refreshCheck(refreshTokenValue)) {
                throw new IllegalArgumentException("Bad refresh token");
            }
        }
        filterChain.doFilter(request, response);
    }
}