package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.entity.*;
import org.example.entity.Module; // Явный импорт твоего класса
import org.example.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final AssignmentRepository assignmentRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    @Override
    public void run(String... args) {
        // 1. Создаем тестового студента
        if (userRepository.count() == 0) {
            User student = new User();
            student.setName("Иван Иванов");
            student.setEmail("ivan@example.com");
            student.setRole("STUDENT");
            userRepository.save(student);
            System.out.println(">>> Студент Иван создан.");
        }

        // 2. Создаем структуру обучения (Курс и Модуль)
        if (courseRepository.count() == 0) {
            Course course = new Course();
            course.setTitle("Основы Java Spring");
            course.setDescription("Курс по разработке REST API");
            courseRepository.save(course);

            // Используем полный путь, чтобы избежать конфликта с java.lang.Module
            org.example.entity.Module module = new org.example.entity.Module();
            module.setTitle("Введение в JPA");
            module.setCourse(course);
            moduleRepository.save(module);

            // 3. Создаем тестовое задание (Assignment)
            if (assignmentRepository.count() == 0) {
                Assignment task = new Assignment();
                task.setTitle("Практика: Создание сущностей");
                task.setDescription("Напишите код для сущности 'User' с использованием аннотаций JPA.");
                assignmentRepository.save(task);
                System.out.println(">>> Тестовое задание создано.");
            }

            // 4. Создаем Тест (Quiz)
            if (quizRepository.count() == 0) {
                Quiz quiz = new Quiz();
                quiz.setTitle("Квиз по синтаксису Java");
                quizRepository.save(quiz);

                Question q1 = new Question();
                q1.setText("Какой ключевой запрос используется для создания таблицы в SQL?");
                q1.setQuiz(quiz);
                questionRepository.save(q1);

                // Варианты ответов
                Option opt1 = new Option();
                opt1.setText("CREATE TABLE");
                opt1.setCorrect(true);
                opt1.setQuestion(q1);
                optionRepository.save(opt1);

                Option opt2 = new Option();
                opt2.setText("MAKE TABLE");
                opt2.setCorrect(false);
                opt2.setQuestion(q1);
                optionRepository.save(opt2);

                System.out.println(">>> Тестовый квиз создан.");
            }

            System.out.println(">>> Полная структура данных готова.");
        }

        System.out.println("-----------------------------------------");
        System.out.println("LMS СИСТЕМА ЗАПУЩЕНА И ГОТОВА К РАБОТЕ");
        System.out.println("-----------------------------------------");
    }
}