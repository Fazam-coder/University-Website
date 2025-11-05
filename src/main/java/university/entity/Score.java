package university.entity;

import university.dto.StudentDto;

public class Score {
    private Long id;
    private String studentName;
    private Lesson lesson;
    private Integer score;

    public Score(Long id, String studentName, Lesson lesson, Integer score) {
        this.id = id;
        this.studentName = studentName;
        this.lesson = lesson;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public Integer getScore() {
        return score;
    }
}
