package fazulzyanov.servlets;

import fazulzyanov.services.LessonScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/add_lesson")
public class AddLessonForAdminServlet extends HttpServlet {
    private LessonScoreService lessonScoreService;

    @Override
    public void init() throws ServletException {
        lessonScoreService = (LessonScoreService)getServletContext().getAttribute("lessonScoreService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("contextPath", req.getContextPath());
        req.getRequestDispatcher("/add_lesson_for_admin.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String group = req.getParameter("group");
        String lesson = req.getParameter("lesson_name");
        String teacher = req.getParameter("teacher_name");
        lessonScoreService.saveLesson(group, lesson, teacher);
        resp.sendRedirect(req.getContextPath() + "/admin/lessons");

    }
}
