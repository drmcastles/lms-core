package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CourseDto;
import org.example.dto.CourseFullDto;
import org.example.dto.ModuleDto;
import org.example.entity.Course;
import org.example.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseFullDto getCourseFullInfo(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Курс не найден"));

        CourseFullDto dto = new CourseFullDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());

        if (course.getModules() != null) {
            dto.setModules(course.getModules().stream().map(m -> {
                ModuleDto mDto = new ModuleDto();
                mDto.setId(m.getId());
                mDto.setTitle(m.getTitle());
                return mDto;
            }).collect(Collectors.toList()));
        }

        return dto;
    }

    @Transactional
    public CourseDto createCourse(CourseDto dto) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setCategoryName(dto.getCategoryName());
        Course saved = courseRepository.save(course);
        return convertToDto(saved);
    }

    private CourseDto convertToDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setCategoryName(course.getCategoryName());
        return dto;
    }
}