package university.servlets;

import university.services.LessonScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/check_lesson_exist")
public class CheckLessonExistsServlet extends HttpServlet {
    private LessonScoreService lessonScoreService;

    @Override
    public void init() throws ServletException {
        lessonScoreService = (LessonScoreService) getServletContext().getAttribute("lessonScoreService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String group = req.getParameter("group");
        String lessonName = req.getParameter("lesson_name");
        String teacher = req.getParameter("teacher");
        resp.setContentType("text/plain");
        if (lessonScoreService.existsLesson(group, lessonName, teacher)) {
            resp.getWriter().write("Такой предмет уже существует");
        } else {
            resp.getWriter().write("");
        }
    }
}
