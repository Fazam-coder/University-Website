package university.servlets;

import university.dto.UserDto;
import university.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("contextPath", req.getContextPath());
        req.getRequestDispatcher("/WEB-INF/view/login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String submit = req.getParameter("submit");
        if (submit.equals("Sign Up")) {
            resp.sendRedirect(req.getContextPath() + "/sign_up");
            // without return, the server crashes
            return;
        }
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (userService.verifyUser(login, password)) {
            // session
            HttpSession httpSession = req.getSession();
            UserDto user = userService.getByLogin(login);
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("role", userService.getRole(login));
            httpSession.setMaxInactiveInterval(60 * 60);

            // cookie
            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(24 * 60 * 60);

            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/users");
            // without return, the server crashes
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }

}
