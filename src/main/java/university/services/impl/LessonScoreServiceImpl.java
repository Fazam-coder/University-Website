package university.services.impl;

import university.dao.LessonScoreDao;
import university.dto.ScoreWithRankDto;
import university.entity.Lesson;
import university.entity.Score;
import university.services.LessonScoreService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class LessonScoreServiceImpl implements LessonScoreService {
    private final LessonScoreDao lessonScoreDao;

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
    public List<Lesson> getLessonsByGroup(String group) {
        return lessonScoreDao.getLessonsByGroup(group);
    }

    @Override
    public Lesson getLessonById(Integer lessonId) {
        return lessonScoreDao.getLessonById(lessonId);
    }

    @Override
    public List<Score> getScoresByLessonId(Integer lessonId) {
        return lessonScoreDao.getScoresByLessonId(lessonId);
    }

    @Override
    public List<Score> getScoresByStudentId(Integer studentId) {
        return lessonScoreDao.getScoresByStudentId(studentId);
    }

    @Override
    public List<ScoreWithRankDto> getScoresByLessonIdWithRank(Integer lessonId) {
        Comparator<Score> comparator = Comparator
                .comparing(Score::getScore)
                .reversed()
                .thenComparing(Score::getStudentName);
        List<Score> scores = getScoresByLessonId(lessonId)
                .stream()
                .sorted(comparator)
                .toList();
        List<ScoreWithRankDto> scoresWithRank = new ArrayList<>();
        int rank = 1;
        for (Score score : scores) {
            scoresWithRank.add(new ScoreWithRankDto(score.getStudentName(), score.getScore(), rank++));
        }
        for (int i = 1; i < scoresWithRank.size(); i++) {
            if (scoresWithRank.get(i - 1).getScore().equals(scoresWithRank.get(i).getScore())) {
                ScoreWithRankDto scoreWithRankDto = scoresWithRank.get(i);
                scoresWithRank.set(i, new ScoreWithRankDto(
                        scoreWithRankDto.getStudentName(),
                        scoreWithRankDto.getScore(),
                        scoresWithRank.get(i - 1).getRank()
                ));
            }
        }
        return scoresWithRank;
    }

    @Override
    public void saveLesson(String group, String lessonName, String teacherName) {
        // default value
        Integer lesson_id = 1;
        Lesson lesson = new Lesson(lesson_id, lessonName, group, teacherName);
        lessonScoreDao.saveLesson(lesson);
    }

    @Override
    public void saveScores(List<Score> newScores) {
        List<Score> oldScores = lessonScoreDao.getScoresByLessonId(newScores.getFirst().getLesson().getId());
        for (Score newScore : newScores) {
            if (newScore.getId() != 0L) {
                for (Score oldScore : oldScores) {
                    if (oldScore.getId().equals(newScore.getId())) {
                        if (!Objects.equals(newScore.getScore(), oldScore.getScore())) {
                            lessonScoreDao.updateScore(newScore);
                        }
                        break;
                    }
                }
            } else {
                lessonScoreDao.saveScore(newScore);
            }
        }
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
