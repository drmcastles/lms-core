package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Assignment;
import org.example.entity.AssignmentSubmission;
import org.example.entity.User;
import org.example.service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
@Validated
public class AssignmentController {

    private final AssignmentService assignmentService;

    /**
     * Сдача работы студентом.
     */
    @PostMapping("/submit")
    public ResponseEntity<String> submitWork(
            @RequestParam Long studentId,
            @RequestParam Long assignmentId,
            @RequestParam String solutionUrl) {

        AssignmentSubmission submission = new AssignmentSubmission();

        // Создаем легковесные объекты (Stub) только с ID для связей JPA
        User student = new User();
        student.setId(studentId);
        submission.setStudent(student);

        Assignment assignment = new Assignment();
        assignment.setId(assignmentId);
        submission.setAssignment(assignment);

        submission.setSolutionUrl(solutionUrl);

        assignmentService.submitWork(submission);
        return ResponseEntity.ok("Решение успешно отправлено на проверку");
    }

    /**
     * Оценка работы преподавателем.
     * Добавлена валидация (Критерий на "5").
     */
    @PatchMapping("/submissions/{submissionId}/grade")
    public ResponseEntity<String> gradeSubmission(
            @PathVariable Long submissionId,
            @RequestParam @Min(0) @Max(100) Integer grade) {

        assignmentService.gradeSubmission(submissionId, grade);
        return ResponseEntity.ok("Оценка " + grade + " успешно выставлена.");
    }
}