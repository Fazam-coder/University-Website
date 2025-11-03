package university.servlets;

import university.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/check_lesson_valid")
public class CheckLessonValidServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String group = req.getParameter("group");
        String teacher = req.getParameter("teacher");
        resp.setContentType("text/plain");
        if (group != null && !group.isEmpty() && !userService.existsGroup(group)) {
            resp.getWriter().write("Такой группы нет");
        } else if (teacher != null && !teacher.isEmpty() && !userService.existsTeacher(teacher)) {
            resp.getWriter().write("Такого учителя нет");
        } else {
            resp.getWriter().write("Все хорошо");
        }
    }
}
