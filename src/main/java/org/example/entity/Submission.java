package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "submissions")
@Data // Проверь, что эта аннотация на месте! Она создает методы setLesson и getLesson
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson; // Поле должно называться именно так

    @Column(columnDefinition = "TEXT")
    private String content;

    private Integer grade;
}