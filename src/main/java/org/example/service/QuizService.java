package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.*;
import org.example.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final OptionRepository optionRepository;
    private final QuizResultRepository quizResultRepository;

    @Transactional
    public QuizResult submitAnswer(User user, Quiz quiz, Long optionId) {
        // Ищем вариант ответа
        Option selectedOption = optionRepository.findById(optionId)
                .orElseThrow(() -> new RuntimeException("Вариант ответа не найден"));

        // Считаем баллы
        int score = selectedOption.isCorrect() ? 100 : 0;

        // Создаем результат
        QuizResult result = new QuizResult();


        result.setStudent(user);

        result.setQuiz(quiz);
        result.setScore(score);
        result.setCompletedAt(LocalDateTime.now());

        return quizResultRepository.save(result);
    }
}