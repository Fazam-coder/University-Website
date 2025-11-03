package university.services;

import university.entity.Lesson;

import java.util.List;

public interface LessonScoreService {
    List<Lesson> getAllLessons();

    void saveLesson(String group, String lessonName, String teacherName);

    void updateLessons(List<Lesson> newLessons);

    void deleteLesson(Integer lessonId);

    boolean existsLesson(String group, String lessonName, String teacherName);
}
