package university.servlets;

import university.dto.StudentDto;
import university.entity.Lesson;
import university.entity.Score;
import university.services.LessonScoreService;
import university.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/teacher/scores")
public class ScoresForTeacherServlet extends HttpServlet {
    private LessonScoreService lessonScoreService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        lessonScoreService = (LessonScoreService) getServletContext().getAttribute("lessonScoreService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer lessonId = Integer.parseInt(req.getParameter("id"));
            Lesson lesson = lessonScoreService.getLessonById(lessonId);
            List<Score> scores = lessonScoreService.getScoresByLessonId(lessonId);
            String group = lesson.getGroup();
            List<StudentDto> students = userService.getStudentsByGroup(group);
            for (StudentDto student : students) {
                boolean f = false;
                for (Score score : scores) {
                    f = f || score.getStudentName().equals(student.getName());
                    if (f) break;
                }
                if (!f) {
                    scores.add(new Score(0L, student.getName(), lesson, 0));
                }
            }
            req.setAttribute("lesson", lesson);
            req.setAttribute("scores", scores);
            req.setAttribute("contextPath", req.getContextPath());
            req.getRequestDispatcher("/WEB-INF/view/scores_for_teacher.ftl").forward(req, resp);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer lessonId = Integer.parseInt(req.getParameter("id"));
            Lesson lesson = lessonScoreService.getLessonById(lessonId);
            List<Score> scores = lessonScoreService.getScoresByLessonId(lessonId);
            String group = lesson.getGroup();
            List<StudentDto> students = userService.getStudentsByGroup(group);
            List<Score> newScores = new ArrayList<>();
            for (Score score : scores) {
                Integer scoreInt = Integer.parseInt(req.getParameter("score_" + score.getId()));
                newScores.add(new Score(score.getId(), score.getStudentName(), lesson, scoreInt));
            }
            for (StudentDto student : students) {
                boolean f = false;
                for (Score score : scores) {
                    f = f || score.getStudentName().equals(student.getName());
                    if (f) break;
                }
                if (!f) {
                    Integer scoreInt = Integer.parseInt(req.getParameter("score_" + student.getName()));
                    newScores.add(new Score(0L, student.getName(), lesson, scoreInt));
                }
            }
            lessonScoreService.saveScores(newScores);
            resp.sendRedirect(req.getContextPath() + "/teacher/lessons");
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
