package fazulzyanov.services.impl;

import fazulzyanov.dao.LessonScoreDao;
import fazulzyanov.entity.Lesson;
import fazulzyanov.services.LessonScoreService;

import java.util.List;

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
    public void saveLesson(String group, String lessonName, String teacherName) {

    }

    @Override
    public void updateLessons(List<Lesson> newLessons) {

    }

    @Override
    public void deleteLesson(Integer lessonId) {

    }
}
