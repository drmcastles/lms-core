package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.entity.*;
import org.example.repository.*;
import org.example.service.CourseService;
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
    private final CategoryRepository categoryRepository; // Добавили
    private final CourseService courseService; // Добавили для проверки регистрации

    @Override
    public void run(String... args) {
        // 1. Создаем категорию (нужна для связи в Course)
        Category category = null;
        if (categoryRepository.count() == 0) {
            category = new Category();
            category.setName("Программирование");
            category = categoryRepository.save(category);
        } else {
            category = categoryRepository.findAll().get(0);
        }

        // 2. Создаем тестового студента
        User student = null;
        if (userRepository.count() == 0) {
            student = new User();
            student.setName("Иван Иванов");
            student.setEmail("ivan@example.com");
            student.setRole("STUDENT");
            student = userRepository.save(student);
            System.out.println(">>> Студент Иван создан.");
        } else {
            student = userRepository.findAll().get(0);
        }

        // 3. Создаем структуру обучения
        if (courseRepository.count() == 0) {
            Course course = new Course();
            course.setTitle("Основы Java Spring");
            course.setDescription("Курс по разработке REST API");
            course.setCategory(category); // Устанавливаем категорию
            course = courseRepository.save(course);

            org.example.entity.Module module = new org.example.entity.Module();
            module.setTitle("Введение в JPA");
            module.setCourse(course);
            moduleRepository.save(module);

            // ПРОВЕРКА РЕГИСТРАЦИИ (Критерий №3)
            courseService.enrollStudent(student.getId(), course.getId());
            System.out.println(">>> Студент записан на курс!");

            // 4. Задания
            if (assignmentRepository.count() == 0) {
                Assignment task = new Assignment();
                task.setTitle("Практика: Создание сущностей");
                task.setDescription("Напишите код для сущности 'User'.");
                assignmentRepository.save(task);
            }

            // 5. Квиз
            if (quizRepository.count() == 0) {
                Quiz quiz = new Quiz();
                quiz.setTitle("Квиз по синтаксису Java");
                quiz = quizRepository.save(quiz);

                Question q1 = new Question();
                q1.setText("Какой запрос используется для создания таблицы?");
                q1.setQuiz(quiz);
                questionRepository.save(q1);

                Option opt1 = new Option();
                opt1.setText("CREATE TABLE");
                opt1.setCorrect(true);
                opt1.setQuestion(q1);
                optionRepository.save(opt1);
            }

            System.out.println(">>> Полная структура данных готова.");
        }

        System.out.println("-----------------------------------------");
        System.out.println("LMS СИСТЕМА ЗАПУЩЕНА И ГОТОВА К РАБОТЕ");
        System.out.println("-----------------------------------------");
    }
}