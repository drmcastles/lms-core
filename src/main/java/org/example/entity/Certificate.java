package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "certificates")
@Getter
@Setter
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String certificateNumber;
    private LocalDate issueDate;

    @OneToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;
}