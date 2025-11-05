package university.dao.impl;

import university.dao.LessonScoreDao;
import university.dto.StudentDto;
import university.entity.Lesson;
import university.entity.Score;
import university.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonScoreDaoImpl implements LessonScoreDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public List<Lesson> getAllLessons() {
        String sql = "select lessons.id, lesson, group_name, name " +
                "from lessons inner join users on users.id = teacher_id order by users.name";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Lesson> lessons = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    lessons.add(
                            new Lesson(
                                    resultSet.getInt("id"),
                                    resultSet.getString("lesson"),
                                    resultSet.getString("group_name"),
                                    resultSet.getString("name")
                            )
                    );
                }
            }
            return lessons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Lesson getLesson(String group, String lessonName) {
        String sql = "select lessons.id, name from lessons inner join users on users.id = teacher_id " +
                "where lessons.name = ? and group_name = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lessonName);
            preparedStatement.setString(2, group);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return new Lesson(resultSet.getInt("lessons.id"), lessonName, group, resultSet.getString("name"));
            }
            throw new IllegalArgumentException("Lesson not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Lesson> getLessonsByTeacherLogin(String login) {
        String sql = "select *, lessons.id as l_id from lessons inner join users on users.id = lessons.teacher_id where login = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            List<Lesson> lessons = new ArrayList<>();
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    lessons.add(
                            new Lesson(
                                    resultSet.getInt("l_id"),
                                    resultSet.getString("lesson"),
                                    resultSet.getString("group_name"),
                                    resultSet.getString("name")
                            )
                    );
                }
            }
            return lessons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Lesson getLessonById(Integer lessonId) {
        String sql = "select *, lessons.id as l_id from lessons inner join users on users.id = lessons.teacher_id where lessons.id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, lessonId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                Lesson lesson = new Lesson(
                        resultSet.getInt("l_id"),
                        resultSet.getString("lesson"),
                        resultSet.getString("group_name"),
                        resultSet.getString("name")
                );
                return lesson;
            }
            throw new IllegalArgumentException("Lesson not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> getScoresByLessonId(Integer lessonId) {
        String sql = "select *, scores.id as s_id, lessons.id as l_id " +
                "from scores inner join lessons on lessons.id = lesson_id inner join users on users.id = student_id " +
                "where lesson_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, lessonId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Score> scores = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Lesson lesson = new Lesson(
                            resultSet.getInt("l_id"),
                            resultSet.getString("lesson"),
                            resultSet.getString("group_name"),
                            resultSet.getString("name")
                    );
                    scores.add(new Score(
                            resultSet.getLong("s_id"),
                            resultSet.getString("name"),
                            lesson,
                            resultSet.getInt("score")
                    ));
                }
            }
            return scores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> getAllScores() {
        return List.of();
    }

    @Override
    public Score getScore(Integer studentId, String lessonName) {
        return null;
    }

    @Override
    public void saveLesson(Lesson lesson) {
        String sql = "insert into lessons (group_name, lesson, teacher_id) " +
                "values (?, ?, (select id from users where name = ? limit 1))";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lesson.getGroup());
            preparedStatement.setString(2, lesson.getLessonName());
            preparedStatement.setString(3, lesson.getTeacherName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveScore(Score score) {
        String sql = "insert into scores (student_id, lesson_id, score) " +
                "values ((select id from users where name = ? limit 1), " +
                "(select id from lessons where group_name = ? and lesson = ? limit 1), ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, score.getStudentName());
            preparedStatement.setString(2, score.getLesson().getGroup());
            preparedStatement.setString(3, score.getLesson().getLessonName());
            preparedStatement.setInt(4, score.getScore());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateScore(Score score) {
        String sql = "update scores set score = ? where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, score.getScore());
            preparedStatement.setLong(2, score.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLesson(Lesson lesson) {
        String sql = "update lessons " +
                "set lesson = ?, group_name = ?, teacher_id = (select id from users where name = ? limit 1) " +
                "where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lesson.getLessonName());
            preparedStatement.setString(2, lesson.getGroup());
            preparedStatement.setString(3, lesson.getTeacherName());
            preparedStatement.setInt(4, lesson.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteLesson(Integer lessonId) {
        String sql = "delete from lessons where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, lessonId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
