package fazulzyanov.dao.impl;

import fazulzyanov.dao.LessonScoreDao;
import fazulzyanov.entity.Lesson;
import fazulzyanov.entity.Score;

import java.util.List;

public class LessonScoreDaoImpl implements LessonScoreDao {
    @Override
    public List<Lesson> getAllLessons() {
        return List.of();
    }

    @Override
    public Lesson getLesson(String group, String lessonName) {
        return null;
    }

    @Override
    public List<Score> getAllScores() {
        return List.of();
    }

    @Override
    public List<Score> getGroupScores() {
        return List.of();
    }

    @Override
    public Score getScore(Integer studentId, String lessonName) {
        return null;
    }

    @Override
    public void saveLesson(Lesson lesson) {

    }

    @Override
    public void saveScore(Score score) {

    }

    @Override
    public void updateScore(Integer studentId, Integer lessonId, Integer newScore) {

    }
}
