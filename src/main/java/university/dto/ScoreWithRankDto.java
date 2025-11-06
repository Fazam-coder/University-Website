package university.dto;

import university.entity.Score;

public class ScoreWithRankDto {
    private String studentName;
    private Integer score;
    private Integer rank;

    public ScoreWithRankDto(String studentName, Integer score, Integer rank) {
        this.studentName = studentName;
        this.score = score;
        this.rank = rank;
    }

    public String getStudentName() {
        return studentName;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getRank() {
        return rank;
    }
}
