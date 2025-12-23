package org.example.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EnrollmentResponse {
    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDateTime enrollmentDate;
    private String status;
}