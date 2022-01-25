package ru.itis.javalab.statementsservice.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.javalab.statementsservice.services.JwtBlacklistService;
import ru.itis.javalab.statementsservice.services.RefreshTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtBlacklistService service;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = request.getHeader("TOKEN");
        if (token != null) {
            if (service.exists(token)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            if (refreshTokenService.accessCheck(token)) {
                JwtAuthentication tokenAuthentication = new JwtAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
            } else {
                httpResponse.sendRedirect("/refresh");
            }

        }
        filterChain.doFilter(request, response);
    }
}
