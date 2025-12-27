package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    private LocalDateTime enrollmentDate = LocalDateTime.now();
}