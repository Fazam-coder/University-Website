package fazulzyanov.servlets;

import fazulzyanov.entity.Lesson;
import fazulzyanov.services.LessonScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/lessons")
public class LessonsForAdminServlet extends HttpServlet {
    private LessonScoreService lessonScoreService;

    @Override
    public void init() throws ServletException {
        lessonScoreService = (LessonScoreService) getServletContext().getAttribute("lessonScoreService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("contextPath", req.getContextPath());
        req.setAttribute("lessons", lessonScoreService.getAllLessons());
        req.getRequestDispatcher("/lessons_for_admin.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String submit = req.getParameter("submit");
        if (submit.equals("add_lesson")) {
            resp.sendRedirect(req.getContextPath() + "/admin/add_lesson");
            return;
        }
        if (submit.equals("save")) {
            List<Lesson> lessons = lessonScoreService.getAllLessons();
            List<Lesson> newLessons = new ArrayList<>();
            for (Lesson lesson : lessons) {
                String lessonName = req.getParameter("name_" + lesson.getId());
                String group = req.getParameter("group_" + lesson.getId());
                String teacher = req.getParameter("teacher_" + lesson.getId());
                newLessons.add(new Lesson(lesson.getId(), lessonName, group, teacher));
            }
            lessonScoreService.updateLessons(newLessons);
            resp.sendRedirect(req.getContextPath() + "/admin/lessons");
        }
        if (submit.contains("delete")) {
            Integer lessonId = Integer.parseInt(submit.substring("delete_".length()));
            lessonScoreService.deleteLesson(lessonId);
            resp.sendRedirect(req.getContextPath() + "/admin/lessons");
        }
    }
}
