package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CourseDto;
import org.example.dto.CourseFullDto;
import org.example.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDto> getAll() {
        return courseService.getAllCourses();
    }


    @GetMapping("/{id}")
    public CourseFullDto getById(@PathVariable Long id) {
        return courseService.getCourseFullInfo(id);
    }

    @PostMapping
    public CourseDto create(@RequestBody CourseDto dto) {
        return courseService.createCourse(dto);
    }
}