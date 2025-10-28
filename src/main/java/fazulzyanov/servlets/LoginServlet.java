package fazulzyanov.servlets;

import fazulzyanov.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("contextPath", req.getContextPath());
        req.getRequestDispatcher("login.ftl").forward(req, resp);
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
            httpSession.setAttribute("user", login);
            httpSession.setMaxInactiveInterval(60 * 60);

            // cookie
            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(24 * 60 * 60);

            resp.addCookie(cookie);
            req.setAttribute("sessionUser", httpSession.getAttribute("user"));
            req.setAttribute("cookies", req.getCookies());
            req.setAttribute("session", httpSession);
            req.getRequestDispatcher("users.ftl").forward(req, resp);
            // without return, the server crashes
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }

}
