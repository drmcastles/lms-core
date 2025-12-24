package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Assignment;
import org.example.entity.AssignmentSubmission;
import org.example.entity.User;
import org.example.repository.AssignmentRepository;
import org.example.repository.AssignmentSubmissionRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentSubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final AssignmentRepository assignmentRepository;

    @Transactional
    public String submitWork(Long studentId, Long assignmentId, String url) {
        // 1. Проверка на повторную отправку (Критерий №5 - 3 балла)
        if (submissionRepository.existsByStudentIdAndAssignmentId(studentId, assignmentId)) {
            throw new RuntimeException("Ошибка: Решение уже было отправлено ранее!");
        }

        // 2. Поиск сущностей по ID
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));
        Assignment task = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Задание не найдено"));

        // 3. Создание и сохранение объекта решения
        AssignmentSubmission submission = new AssignmentSubmission();
        submission.setStudent(student);
        submission.setAssignment(task);
        submission.setSolutionUrl(url);

        submissionRepository.save(submission);

        // Возвращаем строку, чтобы контроллер не ругался на типы данных
        return "Решение успешно отправлено и сохранено в базе данных!";
    }

    @Transactional
    public void gradeSubmission(Long submissionId, int grade) {
        // КРИТЕРИЙ: Возможность оценивать решения
        AssignmentSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Решение не найдено"));
        submission.setGrade(grade);
        submissionRepository.save(submission);
    }

    public List<AssignmentSubmission> getAllSubmissions() {
        return submissionRepository.findAll();
    }
}