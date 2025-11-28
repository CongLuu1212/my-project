package com.t2404e.my_springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "student_score")
public class StudentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Quan hệ N-1 với Student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @NotNull(message = "Student không được để trống")
    private Student student;

    // Quan hệ N-1 với Subject
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    @NotNull(message = "Subject không được để trống")
    private Subject subject;

    @Column(name = "score1", nullable = false)
    @Min(value = 0, message = "Điểm phải >= 0")
    @Max(value = 10, message = "Điểm phải <= 10")
    private Double score1;

    @Column(name = "score2", nullable = false)
    @Min(value = 0, message = "Điểm phải >= 0")
    @Max(value = 10, message = "Điểm phải <= 10")
    private Double score2;

    @Column(name = "grade")
    private String grade;

    // ===== Getter / Setter =====
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Double getScore1() { return score1; }
    public void setScore1(Double score1) { this.score1 = score1; }

    public Double getScore2() { return score2; }
    public void setScore2(Double score2) { this.score2 = score2; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
