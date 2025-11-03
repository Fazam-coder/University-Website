package university.servlets;


import university.dto.UserDto;
import university.entity.Role;
import university.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        try {
            UserDto user;
            if (idParam == null || idParam.isEmpty()) {
                user = userService.getByLogin(req.getSession().getAttribute("user").toString());
            } else {
                user = userService.getById(Integer.parseInt(idParam));
            }
            if (user.getRole() == Role.STUDENT) {
                req.setAttribute("group", userService.getGroup(user.getId()));
            }
            req.setAttribute("user", user);
            req.setAttribute("contextPath", req.getContextPath());
            req.setAttribute("is_can_edit", String.valueOf(
                    (user.getLogin().equals(req.getSession().getAttribute("user").toString())
                    || req.getSession().getAttribute("role") == Role.ADMIN)
            ));
            req.getRequestDispatcher("/WEB-INF/view/profile.ftl").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат ID");
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Пользователь не найден");
        }
    }
}
