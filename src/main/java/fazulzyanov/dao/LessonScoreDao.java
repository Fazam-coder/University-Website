package fazulzyanov.dao;

import fazulzyanov.entity.Lesson;
import fazulzyanov.entity.Score;

import java.util.List;

public interface LessonScoreDao {
    List<Lesson> getAllLessons();

    Lesson getLesson(String group, String lessonName);

    List<Score> getAllScores();

    List<Score> getGroupScores();

    Score getScore(Integer studentId, String lessonName);

    void saveLesson(Lesson lesson);

    void saveScore(Score score);

    void updateScore(Integer studentId, Integer lessonId, Integer newScore);
}
