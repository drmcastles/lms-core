package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
@Data // Генерирует геттеры, сеттеры, toString и equals
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Решение привязано к заданию
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    // Решение привязано к студенту
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private User student;

    @Column(columnDefinition = "TEXT")
    private String content; // Текст решения

    private LocalDateTime submissionDate;

    private String status; // Например: SUBMITTED, GRADED

    private Integer grade; // Оценка (может быть null до проверки)
}