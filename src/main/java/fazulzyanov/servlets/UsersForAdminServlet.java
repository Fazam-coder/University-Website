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
//        resp.setContentType("text/plain");
//        resp.getWriter().println("✅ Admin page works! Context: " + req.getContextPath());
//        return; // НИЧЕГО БОЛЬШЕ НЕ ДЕЛАЕМ
        System.out.println("🔥 UsersForAdminServlet: doGet вызван");
        System.out.println("🔥 UsersForAdminServlet: URI = " + req.getRequestURI());
        System.out.println("🔥 UsersForAdminServlet: QueryString = " + req.getQueryString());
        System.out.println("🔥 UsersForAdminServlet: User-Agent = " + req.getHeader("User-Agent"));
        if (userService == null) {
            System.out.println("USER SERVICE IS NULL");
        }
        List<UserDto> allUsers = userService.getAll();
        for (UserDto user : allUsers) {
            System.out.println(user);
        }
        List<UserDto> nonRoles = allUsers.stream()
                .filter(u -> u.getRole() == Role.USER)
                .toList();
        for (UserDto user : nonRoles) {
            System.out.println(user);
        }
        req.setAttribute("allUsers", allUsers);
        req.setAttribute("nonRoles", nonRoles);
        req.setAttribute("contextPath", req.getServletContext().getContextPath());
        req.getRequestDispatcher("/users_for_admin.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("submit");
        if ("save".equals(action)) {
            // сохранить изменения
        }
        // вернуться на ту же страницу
        resp.sendRedirect(req.getContextPath() + "/admin/users");
    }
}
