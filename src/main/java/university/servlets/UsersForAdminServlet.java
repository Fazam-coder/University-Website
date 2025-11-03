package university.servlets;

import university.dto.StudentDto;
import university.dto.UserDto;
import university.entity.Role;
import university.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<StudentDto> students = userService.getAllStudents();
        req.setAttribute("allUsers", allUsers);
        req.setAttribute("nonRoles", nonRoles);
        req.setAttribute("students", students);
        req.setAttribute("contextPath", req.getServletContext().getContextPath());
        req.getRequestDispatcher("/WEB-INF/view/users_for_admin.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String submit = req.getParameter("submit");
            if ("save_roles".equals(submit)) {
                List<UserDto> nonRoles = userService.getAll().stream()
                        .filter(u -> u.getRole() == Role.USER)
                        .toList();
                for (UserDto user : nonRoles) {
                    String param = req.getParameter("role_" + user.getId());
                    if (param != null && !param.isEmpty()) {
                        Role newRole = Role.valueOf(param);
                        if (newRole != Role.USER) {
                            userService.updateRole(user.getId(), newRole);
                        }
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/admin/users");
            }
            if (submit.contains("delete")) {
                Integer userId = Integer.parseInt(submit.substring("delete_".length()));
                userService.delete(userId);
                resp.sendRedirect(req.getContextPath() + "/admin/users");
            }
            if (submit.equals("save_groups")) {
                List<StudentDto> students = userService.getAllStudents();
                Map<Integer, String> groups = new HashMap<>();
                for (StudentDto student : students) {
                    String param = req.getParameter("group_" + student.getId());
                    groups.put(student.getId(), param);
                }
                userService.saveGroups(groups);
                resp.sendRedirect(req.getContextPath() + "/admin/users");
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}
