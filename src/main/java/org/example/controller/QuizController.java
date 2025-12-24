package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.QuizResult;
import org.example.service.QuizService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;


    @PostMapping("/submit/{optionId}")
    public QuizResult submitAnswer(@PathVariable Long optionId) {


        return quizService.submitAnswer(null, null, optionId);
    }
}