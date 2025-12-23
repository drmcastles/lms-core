package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.*;
import org.example.entity.Course;
import org.example.entity.Module;
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
                .map(this::mapToSimpleDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseFullDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Курс не найден с id: " + id));
        return mapToFullDto(course);
    }

    private CourseDto mapToSimpleDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setTeacherId(course.getTeacherId());
        if (course.getCategory() != null) {
            dto.setCategoryName(course.getCategory().getName());
        }
        return dto;
    }

    private CourseFullDto mapToFullDto(Course course) {
        CourseFullDto dto = new CourseFullDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setTeacherId(course.getTeacherId());

        if (course.getCategory() != null) {
            dto.setCategoryName(course.getCategory().getName());
        }

        if (course.getModules() != null) {
            List<ModuleDto> moduleDtos = course.getModules().stream().map(m -> {
                ModuleDto mDto = new ModuleDto();
                mDto.setId(m.getId());
                mDto.setTitle(m.getTitle());

                if (m.getLessons() != null) {
                    List<LessonDto> lessonDtos = m.getLessons().stream().map(l -> {
                        LessonDto lDto = new LessonDto();
                        lDto.setId(l.getId());
                        lDto.setTitle(l.getTitle());
                        lDto.setContent(l.getContent());
                        return lDto;
                    }).collect(Collectors.toList());
                    mDto.setLessons(lessonDtos);
                }
                return mDto;
            }).collect(Collectors.toList());
            dto.setModules(moduleDtos);
        }
        return dto;
    }
}