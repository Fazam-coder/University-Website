package university.servlets;

import university.entity.Score;
import university.services.LessonScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/scores")
public class ScoresForTeacherServlet extends HttpServlet {
    private LessonScoreService lessonScoreService;

    @Override
    public void init() throws ServletException {
        lessonScoreService = (LessonScoreService) getServletContext().getAttribute("lessonScoreService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer lessonId = Integer.parseInt(req.getParameter("id"));
            List<Score> scores = lessonScoreService.getScoresByLessonId(lessonId);
            req.setAttribute("scores", scores);
            req.setAttribute("contextPath", req.getContextPath());
            req.getRequestDispatcher("/WEB-INF/view/scores_for_teacher.ftl").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
