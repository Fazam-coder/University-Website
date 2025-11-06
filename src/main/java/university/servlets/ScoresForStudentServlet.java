package university.servlets;

import university.dto.ScoreWithRankDto;
import university.dto.UserDto;
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

@WebServlet("/student/lessons_and_scores")
public class ScoresForStudentServlet extends HttpServlet {
    LessonScoreService lessonScoreService;
    UserService userService;

    @Override
    public void init() throws ServletException {
        lessonScoreService = (LessonScoreService) getServletContext().getAttribute("lessonScoreService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto curUser = (UserDto) req.getSession().getAttribute("user");
        String group = userService.getGroup(curUser.getId());
        List<Lesson> lessons = lessonScoreService.getLessonsByGroup(group);
        String tab = req.getParameter("tab");
        if (tab == null) tab = "my-scores";
        List<Score> curScores = lessonScoreService.getScoresByStudentId(curUser.getId());
        List<Lesson> allLessons = lessonScoreService.getLessonsByGroup(group);
        for (Lesson lesson : allLessons) {
            boolean exist = false;
            for (Score score : curScores) {
                if (score.getLesson().getId().equals(lesson.getId())) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                curScores.add(new Score(0L, curUser.getName(), lesson, null));
            }
        }
        Integer selectedLessonId = null;
        Lesson selectedLesson = null;
        List<ScoreWithRankDto> scores = new ArrayList<>();
        Integer myScore = null;
        Integer myRank = null;
        Integer totalStudentsInGroup = 0;
        String currentStudentName = curUser.getName();

        if ("group-rank".equals(tab)) {
            String lessonIdParam = req.getParameter("lessonId");
            if (lessonIdParam != null && !lessonIdParam.isEmpty()) {
                selectedLessonId = Integer.parseInt(lessonIdParam);
                selectedLesson = lessonScoreService.getLessonById(selectedLessonId);
                scores = lessonScoreService.getScoresByLessonIdWithRank(selectedLessonId);

                for (ScoreWithRankDto score : scores) {
                    if (score.getStudentName().equals(currentStudentName)) {
                        myScore = score.getScore();
                        myRank = score.getRank();
                        break;
                    }
                }

                totalStudentsInGroup = userService.getStudentsByGroup(selectedLesson.getGroup()).size();
            }
        }
        req.setAttribute("contextPath", req.getContextPath());
        req.setAttribute("tab", tab);
        req.setAttribute("myScores", curScores);
        req.setAttribute("lessons", lessons);
        req.setAttribute("selectedLessonId", selectedLessonId);
        req.setAttribute("selectedLesson", selectedLesson);
        req.setAttribute("scores", scores);
        req.setAttribute("myScore", myScore);
        req.setAttribute("myRank", myRank);
        req.setAttribute("totalStudentsInGroup", totalStudentsInGroup);
        req.setAttribute("currentStudentName", currentStudentName);
        req.getRequestDispatcher("/WEB-INF/view/scores_for_student.ftl").forward(req, resp);
    }
}
