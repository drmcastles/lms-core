package org.example;

import org.example.entity.Course;
import org.example.entity.Module;
import org.example.repository.CourseRepository;
import org.example.repository.ModuleRepository;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LmsLogicTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    @DisplayName("Тест каскадного удаления: при удалении курса должны удалиться его модули")
    void testCascadeDeleteCourse() {
        // 1. Создаем курс и модуль
        Course course = new Course();
        course.setTitle("Курс для удаления");
        courseRepository.save(course);

        org.example.entity.Module module = new org.example.entity.Module();
        module.setTitle("Модуль, который должен исчезнуть");
        module.setCourse(course);
        moduleRepository.save(module);

        Long courseId = course.getId();
        Long moduleId = module.getId();

        // 2. Удаляем курс
        courseRepository.deleteById(courseId);

        // 3. Проверяем, что курса нет и модуля ТОЖЕ нет (каскад сработал)
        assertFalse(courseRepository.findById(courseId).isPresent(), "Курс не удалился");
        assertFalse(moduleRepository.findById(moduleId).isPresent(), "Модуль остался в базе, каскад не сработал!");

        System.out.println(">>> Тест каскадного удаления успешно пройден!");
    }

    @Test
    @DisplayName("Тест Lazy Loading: доступ к модулям вне транзакции должен вызвать ошибку")
    void testLazyLoadingException() {
        // 1. Подготавливаем данные
        Course course = new Course();
        course.setTitle("Lazy Test Course");
        courseRepository.save(course);

        Long courseId = course.getId();

        // 2. Загружаем курс из базы
        Course loadedCourse = courseRepository.findById(courseId).get();

        // 3. Пытаемся получить модули БЕЗ @Transactional (сессия закрыта)
        // Если в Course.java стоит fetch = FetchType.LAZY, это должно кинуть LazyInitializationException
        assertThrows(Exception.class, () -> {
            System.out.println(loadedCourse.getModules().size());
        }, "Ожидалось исключение ленивой загрузки, но данные загрузились");

        System.out.println(">>> Тест ленивой загрузки успешно подтвержден!");
    }
}