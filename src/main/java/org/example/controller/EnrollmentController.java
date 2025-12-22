package org.example.controller;

import org.example.service.EnrollmentService;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.repository.CourseRepository;
import org.example.entity.Course;
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

    // метод для быстрой подготовки данных (создаст тестового юзера и курс)
    @GetMapping("/init")
    public String init() {
        User student = new User();
        student.setName("Ivan");
        student.setEmail("ivan@example.com");
        userRepository.save(student);

        Course course = new Course();
        course.setTitle("Java Spring Boot");
        courseRepository.save(course);

        return "Данные созданы: Student ID=" + student.getId() + ", Course ID=" + course.getId();
    }

    // метод для записи
    @GetMapping("/enroll")
    public String enroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        return enrollmentService.enrollStudent(studentId, courseId);
    }
}