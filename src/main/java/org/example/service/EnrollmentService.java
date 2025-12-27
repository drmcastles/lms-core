package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.User;
import org.example.repository.CourseRepository;
import org.example.repository.EnrollmentRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public String enrollStudent(Long studentId, Long courseId) {
        // 1. Проверка существования сущностей
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Пользователь с ID " + studentId + " не найден"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курс с ID " + courseId + " не найден"));

        // 2. Проверка на дубликат (пункт ТЗ про предотвращение повторной записи)
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            return "Студент уже записан на курс: " + course.getTitle();
        }

        // 3. Создание записи
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDateTime.now());

        enrollmentRepository.save(enrollment);
        return "Успешно! Студент " + student.getName() + " записан на курс " + course.getTitle();
    }

    @Transactional(readOnly = true)
    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
}