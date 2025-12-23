package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Assignment;
import org.example.entity.Submission;
import org.example.entity.User;
import org.example.repository.AssignmentRepository;
import org.example.repository.SubmissionRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;

    @Transactional
    public String submitWork(Long studentId, Long assignmentId, String content) {
        // 1. Проверяем, существуют ли студент и само задание
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Задание не найдено"));

        // 2. Проверка уникальности (требование ТЗ 5.3)
        if (submissionRepository.existsByStudentIdAndAssignmentId(studentId, assignmentId)) {
            return "Ошибка: Вы уже отправляли решение на это задание!";
        }

        // 3. Создаем и сохраняем решение
        Submission submission = new Submission();
        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setContent(content); // Текст решения
        submission.setSubmissionDate(LocalDateTime.now());
        submission.setStatus("SUBMITTED"); // Статус по ТЗ

        submissionRepository.save(submission);
        return "Успех: Ваше решение принято на проверку!";
    }
}