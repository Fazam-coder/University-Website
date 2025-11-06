package university.services;

import university.dto.ScoreWithRankDto;
import university.entity.Lesson;
import university.entity.Score;

import java.util.List;

public interface LessonScoreService {
    List<Lesson> getAllLessons();

    List<Lesson> getLessonsByTeacherLogin(String teacherLogin);

    List<Lesson> getLessonsByGroup(String group);

    Lesson getLessonById(Integer lessonId);

    List<Score> getScoresByLessonId(Integer lessonId);

    List<Score> getScoresByStudentId(Integer studentId);

    List<ScoreWithRankDto> getScoresByLessonIdWithRank(Integer lessonId);

    void saveLesson(String group, String lessonName, String teacherName);

    void saveScores(List<Score> newScores);

    void updateLessons(List<Lesson> newLessons);

    void deleteLesson(Integer lessonId);

    boolean existsLesson(String group, String lessonName, String teacherName);
}
