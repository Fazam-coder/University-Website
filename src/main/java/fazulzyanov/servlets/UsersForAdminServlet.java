package fazulzyanov.servlets;

import fazulzyanov.dto.UserDto;
import fazulzyanov.entity.Role;
import fazulzyanov.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersForAdmin", urlPatterns = "/admin/users")
public class UsersForAdminServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDto> allUsers = userService.getAll();
        List<UserDto> nonRoles = allUsers.stream()
                .filter(u -> u.getRole() == Role.USER)
                .toList();
        req.setAttribute("allUsers", allUsers);
        req.setAttribute("nonRoles", nonRoles);
        req.getRequestDispatcher("users_for_admin.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
