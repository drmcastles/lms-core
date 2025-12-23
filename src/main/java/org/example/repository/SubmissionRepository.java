package org.example.repository;

import org.example.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    // проверка для логики ТЗ: один студент - одно решение на одно задание
    boolean existsByStudentIdAndAssignmentId(Long studentId, Long assignmentId);
}