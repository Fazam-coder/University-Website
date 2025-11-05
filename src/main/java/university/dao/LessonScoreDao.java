package university.dao;

import university.entity.Lesson;
import university.entity.Score;

import java.util.List;

public interface LessonScoreDao {
    List<Lesson> getAllLessons();

    Lesson getLesson(String group, String lessonName);

    List<Lesson> getLessonsByTeacherLogin(String login);

    Lesson getLessonById(Integer lessonId);

    List<Score> getScoresByLessonId(Integer lessonId);

    List<Score> getAllScores();

    Score getScore(Integer studentId, String lessonName);

    void saveLesson(Lesson lesson);

    void saveScore(Score score);

    void updateScore(Score score);

    void updateLesson(Lesson lesson);

    void deleteLesson(Integer lessonId);
}
