package org.example;

import org.example.entity.*;
import org.example.repository.*;
import org.example.service.QuizService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LmsIntegrationTest {

    @Autowired private UserRepository userRepository;
    @Autowired private QuizRepository quizRepository;
    @Autowired private OptionRepository optionRepository;
    @Autowired private QuizService quizService;
    @Autowired private QuizResultRepository quizResultRepository;

    @Test
    void testFullSystemCycle() {

        User user = new User();
        user.setName("Tester");
        user.setEmail("test@test.com");
        user = userRepository.save(user);


        Quiz quiz = new Quiz();
        quiz.setTitle("Final Exam");
        quiz = quizRepository.save(quiz);


        Option opt = new Option();
        opt.setCorrect(true);
        opt.setText("Correct Answer");
        opt = optionRepository.save(opt);


        QuizResult res = quizService.submitAnswer(user, quiz, opt.getId());


        assertNotNull(res.getId(), "ID результата должен быть создан");
        assertEquals(100, res.getScore(), "Счет должен быть 100 за верный ответ");
        assertEquals("Tester", res.getStudent().getName(), "Имя студента должно совпадать");
        assertNotNull(res.getCompletedAt(), "Дата завершения должна быть установлена");
    }
}