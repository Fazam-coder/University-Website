package university.servlets;

import university.dto.UserDto;
import university.entity.Lesson;
import university.services.LessonScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/lessons")
public class LessonsForTeacherServlet extends HttpServlet {
    private LessonScoreService lessonScoreService;

    @Override
    public void init() throws ServletException {
        lessonScoreService = (LessonScoreService) getServletContext().getAttribute("lessonScoreService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = ((UserDto) req.getSession().getAttribute("user")).getLogin();
        List<Lesson> lessons = lessonScoreService.getLessonsByTeacherLogin(login);
        req.setAttribute("lessons", lessons);
        req.setAttribute("contextPath", req.getContextPath());
        req.getRequestDispatcher("/WEB-INF/view/lessons_for_teacher.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer lessonId = Integer.parseInt(req.getParameter("submit").substring("edit_scores_".length()));
        resp.sendRedirect(req.getContextPath() + "/teacher/scores?id=" + lessonId);
    }
}
