package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.entity.*;

import org.example.entity.Module;
import org.example.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final AssignmentRepository assignmentRepository; // Добавили репозиторий заданий

    @Override
    public void run(String... args) {
        // 1. Создаем тестового студента
        if (userRepository.count() == 0) {
            User student = new User();
            student.setName("Иван Иванов");
            student.setEmail("ivan@example.com");
            student.setRole("STUDENT");
            userRepository.save(student);
        }

        // 2. Создаем тестовый курс и модуль
        if (courseRepository.count() == 0) {
            Course course = new Course();
            course.setTitle("Основы Java Spring");
            course.setDescription("Курс по разработке REST API");
            courseRepository.save(course);

            Module module = new Module();
            module.setTitle("Введение в JPA");
            module.setCourse(course);
            moduleRepository.save(module);

            // 3. Создаем тестовое задание (Assignment) для этого курса/модуля
            if (assignmentRepository.count() == 0) {
                Assignment task = new Assignment();
                task.setTitle("Практика: Создание сущностей");
                task.setDescription("Напишите код для сущности 'User' с использованием аннотаций JPA.");
                // По ТЗ задание может быть связано с уроком или модулем.
                // Для простоты привяжем к модулю, если в твоей сущности есть такое поле:
                // task.setModule(module);

                assignmentRepository.save(task);
                System.out.println(">>> Тестовое задание создано с ID: " + task.getId());
            }

            System.out.println(">>> Тестовый курс и модуль созданы.");
        }

        System.out.println("-----------------------------------------");
        System.out.println("БАЗА ДАННЫХ ГОТОВА: Задания доступны для сдачи");
        System.out.println("-----------------------------------------");
    }
}