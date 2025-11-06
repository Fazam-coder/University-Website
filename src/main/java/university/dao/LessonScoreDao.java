package university.dao;

import university.entity.Lesson;
import university.entity.Score;

import java.util.List;

public interface LessonScoreDao {
    List<Lesson> getAllLessons();

    List<Lesson> getLessonsByTeacherLogin(String login);

    List<Lesson> getLessonsByGroup(String group);

    Lesson getLessonById(Integer lessonId);

    List<Score> getScoresByLessonId(Integer lessonId);

    List<Score> getScoresByStudentId(Integer studentId);

    void saveLesson(Lesson lesson);

    void saveScore(Score score);

    void updateScore(Score score);

    void updateLesson(Lesson lesson);

    void deleteLesson(Integer lessonId);
}
