package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.entity.Category;
import org.example.entity.Course;
import org.example.repository.CategoryRepository;
import org.example.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    // Эти переменные подключают наши репозитории автоматически
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        // Проверяем: если курсов в базе нет, то создаем один тестовый
        if (courseRepository.count() == 0) {

            Category cat = new Category();
            cat.setName("Программирование");
            categoryRepository.save(cat);

            Course course = new Course();
            course.setTitle("Первый тестовый курс");
            course.setDescription("Если вы это видите, значит база данных работает!");
            course.setCategory(cat);
            courseRepository.save(course);

            System.out.println("✅ ТЕСТОВЫЕ ДАННЫЕ УСПЕШНО ЗАГРУЖЕНЫ В POSTGRES!");
        }
    }
}