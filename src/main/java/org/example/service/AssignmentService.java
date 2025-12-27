package org.example.service;

import org.example.entity.AssignmentSubmission;
import org.example.repository.AssignmentSubmissionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentSubmissionRepository submissionRepository;

    @Transactional
    public AssignmentSubmission submitWork(AssignmentSubmission submission) {
        boolean alreadySubmitted = submissionRepository.existsByStudentIdAndAssignmentId(
                submission.getStudent().getId(),
                submission.getAssignment().getId()
        );

        if (alreadySubmitted) {
            throw new IllegalStateException("Вы уже отправили решение для этого задания.");
        }

        return submissionRepository.save(submission);
    }

    @Transactional
    public void gradeSubmission(Long submissionId, Integer grade) {
        AssignmentSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new EntityNotFoundException("Решение не найдено"));

        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Оценка должна быть от 0 до 100");
        }

        submission.setGrade(grade);
    }
}