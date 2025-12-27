package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Enrollment;
import org.example.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // Регистрация студента на курс
    @PostMapping("/enroll")
    public String enroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        return enrollmentService.enrollStudent(studentId, courseId);
    }

    // Просмотр курсов, на которые записан студент (пункт ТЗ 4.4)
    @GetMapping("/student/{studentId}")
    public List<Enrollment> getStudentEnrollments(@PathVariable Long studentId) {
        return enrollmentService.getEnrollmentsByStudent(studentId);
    }
}