package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.entity.Course;
import org.example.entity.Module;
import org.example.entity.User;
import org.example.repository.CourseRepository;
import org.example.repository.ModuleRepository;
import org.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;

    @Override
    public void run(String... args) {
        // 1. Создаем тестового студента (чтобы studentId=1 работал)
        if (userRepository.count() == 0) {
            User student = new User();
            student.setName("Иван Иванов");
            student.setEmail("ivan@example.com");
            // Если в твоей сущности есть поле role, раскомментируй строку ниже:
            // student.setRole("STUDENT");
            userRepository.save(student);
            System.out.println(">>> Создан тестовый студент с ID: " + student.getId());
        }

        // 2. Создаем тестовый курс (чтобы courseId=1 работал)
        if (courseRepository.count() == 0) {
            Course course = new Course();
            course.setTitle("Основы Java Spring");
            course.setDescription("Курс по разработке REST API");
            courseRepository.save(course);

            // Добавим сразу модуль к этому курсу
            Module module = new Module();
            module.setTitle("Введение в JPA");
            module.setCourse(course);
            moduleRepository.save(module);

            System.out.println(">>> Создан тестовый курс с ID: " + course.getId());
        }

        System.out.println("-----------------------------------------");
        System.out.println("БАЗА ДАННЫХ ГОТОВА К ТЕСТИРОВАНИЮ");
        System.out.println("Используйте studentId=1 и courseId=1 в Swagger");
        System.out.println("-----------------------------------------");
    }
}