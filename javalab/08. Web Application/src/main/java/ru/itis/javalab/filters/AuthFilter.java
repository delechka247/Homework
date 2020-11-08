package ru.itis.javalab.filters;

import ru.itis.javalab.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebFilter(servletNames = {"profile-servlet"})
public class AuthFilter implements Filter {
    private FilterConfig config;
    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //cookie
        /*
        Cookie[] cookies = request.getCookies();
        UUID authCookieValue = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("authCookie".equals(cookie.getName())) {
                    authCookieValue = UUID.fromString(cookie.getValue());
                }
            }
        }

        if (authCookieValue != null && usersService.getOneByUUID(authCookieValue).size() > 0
                || request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
         */

        HttpSession session = request.getSession(false);
        Boolean sessionExists = session != null;
        UUID uuid = null;

        if (sessionExists) {
            uuid = UUID.fromString(session.getAttribute("auth").toString());
        }

        if (uuid != null && usersService.getOneByUUID(uuid).size() > 0
                    || request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }

    @Override
    public void destroy() {

    }
}
