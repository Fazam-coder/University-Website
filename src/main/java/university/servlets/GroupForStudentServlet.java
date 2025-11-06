package university.servlets;

import university.dto.StudentDto;
import university.dto.UserDto;
import university.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student/group")
public class GroupForStudentServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String group = userService.getGroup(((UserDto) req.getSession().getAttribute("user")).getId());
        List<StudentDto> students = userService.getStudentsByGroup(group);
        req.setAttribute("students", students);
        req.setAttribute("group", group);
        req.setAttribute("contextPath", req.getContextPath());
        req.getRequestDispatcher("/WEB-INF/view/group_for_student.ftl").forward(req, resp);
    }
}
