package fazulzyanov.entity;

public class Lesson {
    private Integer id;
    private String lessonName;
    private String group;
    private String teacherName;

    public Lesson(Integer id, String lessonName, String group, String teacherName) {
        this.id = id;
        this.lessonName = lessonName;
        this.group = group;
        this.teacherName = teacherName;
    }

    public Integer getId() {
        return id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getGroup() {
        return group;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
