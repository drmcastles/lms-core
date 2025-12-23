package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Option;
import org.example.repository.OptionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final OptionRepository optionRepository;

    public String checkAnswer(Long optionId) {
        Option selectedOption = optionRepository.findById(optionId)
                .orElseThrow(() -> new RuntimeException("Вариант ответа не найден"));

        if (selectedOption.isCorrect()) {
            return "Верно! Поздравляем.";
        } else {
            return "Неверно. Попробуйте еще раз.";
        }
    }
}