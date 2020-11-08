package ru.itis.javalab.servlets;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.LoginService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private PasswordEncoder passwordEncoder;
    private UsersService usersService;
    private LoginService loginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
        this.passwordEncoder = (PasswordEncoder) servletContext.getAttribute("passwordEncoder");
        this.loginService = (LoginService) servletContext.getAttribute("loginService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //cookie
        /*
        if (email.equals("gmail@gmail.com") && password.equals("qwerty12")){
            //UUID uuid = UUID.randomUUID();
            //System.out.println(uuid);
            Cookie authCookie = new Cookie("authCookie", "f79af1a2-1b24-47c4-867b-a6904630c071");
            resp.addCookie(authCookie);
            String path = "/profile";
            resp.sendRedirect(req.getContextPath() + path);
        }
         */


        String loginStatus = loginService.login(email, password);
        if (loginStatus.equals("ok")){
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("auth", loginService.getUUIDForSession(email).toString());

            String path = "/profile";
            resp.sendRedirect(req.getContextPath() + path);
        } else {
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("Login failed! Please try again");
            printWriter.println(loginStatus);
            printWriter.close();
        }



        //System.out.println(passwordEncoder.encode("qwerty12"));


    }
}
