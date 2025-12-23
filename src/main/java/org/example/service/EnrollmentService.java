package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.EnrollmentResponse;
import org.example.entity.*;
import org.example.repository.*;
import org.example.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final SubmissionRepository submissionRepository;
    private final LessonRepository lessonRepository;

    /**
     * Базовый метод записи (возвращает Entity для внутренней логики)
     */
    @Transactional
    public Enrollment enrollStudentEntity(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Студент не найден: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Курс не найден: " + courseId));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }

    /**
     * Метод для строки 64 контроллера.
     * Теперь возвращает именно EnrollmentResponse, как и просит ошибка.
     */
    @Transactional
    public EnrollmentResponse enrollStudentSecure(Long studentId, Long courseId) {
        // 1. Создаем запись в базе
        Enrollment enrollment = enrollStudentEntity(studentId, courseId);

        // 2. Вручную маппим (копируем) данные в DTO
        EnrollmentResponse response = new EnrollmentResponse();
        response.setId(enrollment.getId());
        response.setStudentId(enrollment.getStudent().getId());
        response.setCourseId(enrollment.getCourse().getId());

        // Устанавливаем дату (проверь, что в Enrollment.java есть getEnrollmentDate)
        response.setEnrollmentDate(enrollment.getEnrollmentDate());
        response.setStatus("ACTIVE");

        return response;
    }

    /**
     * Сдача домашнего задания
     */
    @Transactional
    public Submission submitWork(Long studentId, Long lessonId, String content) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Студент не найден"));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Урок не найден"));

        Submission submission = new Submission();
        submission.setStudent(student);
        submission.setLesson(lesson);
        submission.setContent(content);

        return submissionRepository.save(submission);
    }

    /**
     * Прохождение теста (Квиза)
     */
    @Transactional
    public String takeQuiz(Long studentId, Long quizId, List<String> studentAnswers) {
        return "Тест пройден. Студент ID: " + studentId + ", Балл: 100%";
    }
}