package university.services.impl;

import university.dao.LessonScoreDao;
import university.entity.Lesson;
import university.entity.Score;
import university.services.LessonScoreService;

import java.util.List;
import java.util.Objects;

public class LessonScoreServiceImpl implements LessonScoreService {
    private LessonScoreDao lessonScoreDao;

    public LessonScoreServiceImpl(LessonScoreDao lessonScoreDao) {
        this.lessonScoreDao = lessonScoreDao;
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonScoreDao.getAllLessons();
    }

    @Override
    public List<Lesson> getLessonsByTeacherLogin(String teacherLogin) {
        return lessonScoreDao.getLessonsByTeacherLogin(teacherLogin);
    }

    @Override
    public List<Score> getScoresByLessonId(Integer lessonId) {
        return lessonScoreDao.getScoresByLessonId(lessonId);
    }

    @Override
    public void saveLesson(String group, String lessonName, String teacherName) {
        // default value
        Integer lesson_id = 1;
        Lesson lesson = new Lesson(lesson_id, lessonName, group, teacherName);
        lessonScoreDao.saveLesson(lesson);
    }

    @Override
    public void updateLessons(List<Lesson> newLessons) {
        List<Lesson> oldLessons = lessonScoreDao.getAllLessons();
        for (Lesson lesson : oldLessons) {
            int i = 0;
            while (!Objects.equals(lesson.getId(), newLessons.get(i).getId())) {
                i++;
            }
            if (!lesson.equals(newLessons.get(i))) {
                lessonScoreDao.updateLesson(newLessons.get(i));
            }
        }
    }

    @Override
    public void deleteLesson(Integer lessonId) {
        lessonScoreDao.deleteLesson(lessonId);
    }

    @Override
    public boolean existsLesson(String group, String lessonName, String teacherName) {
        List<Lesson> lessons = lessonScoreDao.getAllLessons();
        for (Lesson lesson : lessons) {
            if (lesson.getGroup().equals(group)
                    && lesson.getLessonName().equals(lessonName)
                    && lesson.getTeacherName().equals(teacherName)) {
                return true;
            }
        }
        return false;
    }
}
