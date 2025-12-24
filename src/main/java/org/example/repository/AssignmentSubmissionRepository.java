package org.example.repository;

import org.example.entity.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {
    // Этот метод нужен для проверки уникальности (одна попытка на задание)
    boolean existsByStudentIdAndAssignmentId(Long studentId, Long assignmentId);
}