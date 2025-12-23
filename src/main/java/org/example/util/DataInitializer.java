package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.entity.*;
import org.example.entity.Module;
import org.example.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public void run(String... args) {
        try {
            System.out.println("🚀 Начинаем чистую вставку данных...");

            // 1. Категория
            Category cat = new Category();
            cat.setName("Backend Development");
            categoryRepository.save(cat);

            // 2. Курс
            Course course = new Course();
            course.setTitle("Spring Boot Masterclass");
            course.setCategory(cat);
            courseRepository.save(course);

            // 3. Модуль
            Module module = new Module();
            module.setTitle("Основы JPA");
            module.setCourse(course);
            module = moduleRepository.save(module);

            // 4. Урок
            Lesson lesson = new Lesson();
            lesson.setTitle("Настройка сущностей");
            lesson.setContent("В этом уроке мы изучим аннотации @Entity и @Table...");
            lesson.setModule(module);
            lessonRepository.save(lesson);

            // 5. Квиз
            Quiz quiz = new Quiz();
            quiz.setTitle("Тест по JPA");
            quiz.setModule(module);
            quiz = quizRepository.save(quiz);

            // 6. Вопрос для квиза
            Question q = new Question();
            q.setText("Что делает аннотация @Table?");
            q.setCorrectAnswer("Указывает имя таблицы в БД");
            q.setQuiz(quiz);
            questionRepository.save(q);

            System.out.println("✅ БАЗА ДАННЫХ УСПЕШНО ЗАПОЛНЕНА!");

        } catch (Exception e) {
            System.err.println("❌ ОШИБКА ПРИ ЗАПОЛНЕНИИ:");
            e.printStackTrace();
        }
    }
}