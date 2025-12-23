package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.AssignmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    // Метод для отправки решения студентом
    @PostMapping("/{assignmentId}/submit")
    public String submitSolution(
            @PathVariable Long assignmentId,
            @RequestParam Long studentId,
            @RequestBody String content) {
        return assignmentService.submitWork(studentId, assignmentId, content);
    }
}