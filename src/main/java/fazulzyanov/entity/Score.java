package fazulzyanov.entity;

public class Score {
    private Long id;
    private Integer studentId;
    private Lesson lesson;
    private Integer score;

    public Score(Long id, Integer studentId, Lesson lesson, Integer score) {
        this.id = id;
        this.studentId = studentId;
        this.lesson = lesson;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public Integer getScore() {
        return score;
    }
}
