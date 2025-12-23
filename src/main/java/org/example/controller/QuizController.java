package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.QuizService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/answer/{optionId}")
    public String submitAnswer(@PathVariable Long optionId) {
        return quizService.checkAnswer(optionId);
    }
}