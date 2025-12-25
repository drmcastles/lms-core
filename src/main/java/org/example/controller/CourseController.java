package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Course;
import org.example.service.CourseService;
import org.example.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseRepository courseRepository;

    @GetMapping
    public List<Course> getAllCourses(@RequestParam(required = false) Long categoryId) {
        try {
            if (categoryId != null) {
                // Прямой вызов репозитория для фильтрации
                return courseRepository.findByCategoryId(categoryId);
            }
            return courseService.getAllCourses();
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
}