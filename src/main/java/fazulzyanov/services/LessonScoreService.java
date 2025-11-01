package fazulzyanov.services;

import fazulzyanov.entity.Lesson;

import java.util.List;

public interface LessonScoreService {
    List<Lesson> getAllLessons();

    void saveLesson(String group, String lessonName, String teacherName);

    void updateLessons(List<Lesson> newLessons);

    void deleteLesson(Integer lessonId);
}
