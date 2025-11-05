package university.services;

import university.entity.Lesson;
import university.entity.Score;

import java.util.List;

public interface LessonScoreService {
    List<Lesson> getAllLessons();

    List<Lesson> getLessonsByTeacherLogin(String teacherLogin);

    Lesson getLessonById(Integer lessonId);

    List<Score> getScoresByLessonId(Integer lessonId);

    void saveLesson(String group, String lessonName, String teacherName);

    void saveScores(List<Score> newScores);

    void updateLessons(List<Lesson> newLessons);

    void deleteLesson(Integer lessonId);

    boolean existsLesson(String group, String lessonName, String teacherName);
}
