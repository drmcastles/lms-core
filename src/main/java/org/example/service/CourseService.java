package org.example.service;

import org.example.entity.Course;
import org.example.entity.User;
import org.example.repository.CourseRepository;
import org.example.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    /**
     * Возвращает список всех сущностей Course.
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * Находит одну сущность Course по ID или выбрасывает ошибку.
     */
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Курс с ID " + id + " не найден"));
    }

    /**
     * Создает новый курс.
     */
    @Transactional
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * Записывает студента на курс (Many-to-Many).
     */
    @Transactional
    public void enrollStudent(Long userId, Long courseId) {
        User student = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Студент не найден"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Курс не найден"));

        // Добавляем студента в коллекцию курса
        course.getStudents().add(student);

        // Сохраняем изменения
        courseRepository.save(course);
    }
}