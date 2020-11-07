package ru.itis.javalab.servlets;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private PasswordEncoder passwordEncoder;
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
        this.passwordEncoder = (PasswordEncoder) servletContext.getAttribute("passwordEncoder");
    }
/*
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(usersService.getAllUsersByAge(18));
    }
*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false) != null) {
            System.out.println(request.getSession(false).getAttribute("Hello"));
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("Hello", "Hello from server!");
        }
        // false - если сессии не было, то вернет null
        // если true, то он создаст сессию и вернет ее
        // если ничего не указано, то либо вернет существующую, либо создаст новую


        List<User> users = usersService.getAllUsersByAge(18);
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String hashPassword = passwordEncoder.encode(password);
        System.out.println(hashPassword);
        System.out.println(passwordEncoder.matches("qwerty007", hashPassword));

        String color = req.getParameter("color");
        Cookie cookie = new Cookie("color", color);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(cookie);
        resp.sendRedirect("/users");
    }

}

