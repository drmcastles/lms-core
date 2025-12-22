package org.example.controller;

import org.example.dto.EnrollmentResponse;
import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.User;
import org.example.repository.CourseRepository;
import org.example.repository.EnrollmentRepository;
import org.example.repository.UserRepository;
import org.example.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // 1. создание начальных данных
    @GetMapping("/init")
    public String init() {
        User student = new User();
        student.setName("Ivan");
        student.setEmail("ivan@example.com");
        userRepository.save(student);

        Course course = new Course();
        course.setTitle("Java Spring Boot");
        courseRepository.save(course);

        return "Данные созданы! Student ID=" + student.getId() + ", Course ID=" + course.getId();
    }

    // 2. эндпоинт для ошибки (LazyInitializationException)
    @GetMapping("/test-lazy-error")
    public String testLazyError(@RequestParam Long studentId, @RequestParam Long courseId) {
        // Сначала создаем запись через сервис (транзакция там откроется и закроется)
        Enrollment enrollment = enrollmentService.enrollStudentEntity(studentId, courseId);
        Long savedId = enrollment.getId();

        // принудительно достаем эту запись из базы ВНЕ транзакции сервиса.
        //  сессия закроется сразу после findById.
        Enrollment freshEnrollment = enrollmentRepository.findById(savedId)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));

        // ТУТ БУДЕТ ОШИБКА: обращение к прокси-объекту студента без открытой сессии
        return "Имя студента: " + freshEnrollment.getStudent().getName();
    }

    // 3. эндпоинт, который работает ПРАВИЛЬНО через DTO
    @GetMapping("/enroll-safe")
    public EnrollmentResponse enrollSafe(@RequestParam Long studentId, @RequestParam Long courseId) {
        return enrollmentService.enrollStudentSecure(studentId, courseId);
    }
}