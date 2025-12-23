package org.example;

import lombok.RequiredArgsConstructor;
import org.example.entity.Category;
import org.example.entity.Course;
import org.example.entity.Lesson;
import org.example.entity.Module; // Явный импорт твоего класса
import org.example.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            CourseRepository courseRepository,
            CategoryRepository categoryRepository,
            ModuleRepository moduleRepository,
            LessonRepository lessonRepository) {
        return args -> {
            if (courseRepository.count() > 0) {
                System.out.println("⚠️ Данные уже на месте, вставка не требуется.");
                return;
            }

            System.out.println("🚀 Начинаем чистую вставку данных...");

            Category cat = new Category();
            cat.setName("Backend Development");
            categoryRepository.save(cat);

            Course course = new Course();
            course.setTitle("Spring Boot Masterclass");
            course.setDescription("Изучаем Spring Boot с нуля до профи");
            course.setCategory(cat);
            courseRepository.save(course);

            // Теперь конфликта не будет, так как импорт выше указывает на твой Entity
            Module module = new Module();
            module.setTitle("Основы JPA");
            module.setCourse(course);
            moduleRepository.save(module);

            Lesson lesson = new Lesson();
            lesson.setTitle("Настройка сущностей");
            lesson.setContent("Контент урока про @Entity...");
            lesson.setModule(module);
            lessonRepository.save(lesson);

            System.out.println("✅ БАЗА ДАННЫХ УСПЕШНО ЗАПОЛНЕНА!");
        };
    }
}