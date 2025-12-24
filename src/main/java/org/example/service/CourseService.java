package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CourseDto;
import org.example.dto.CourseFullDto;
import org.example.dto.ModuleDto;
import org.example.entity.Course;
import org.example.entity.User;
import org.example.repository.CourseRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

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
        // Если нужно сохранять категорию из DTO, здесь обычно ищут категорию в репозитории
        Course saved = courseRepository.save(course);
        return convertToDto(saved);
    }

    @Transactional
    public void enrollStudent(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курс не найден"));

        if (!course.getStudents().contains(student)) {
            course.getStudents().add(student);
            student.getCourses().add(course);
            courseRepository.save(course);
        }
    }

    @Transactional
    public void unenrollStudent(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курс не найден"));

        course.getStudents().remove(student);
        student.getCourses().remove(course);
        courseRepository.save(course);
    }

    @Transactional(readOnly = true)
    public List<User> getCourseStudents(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курс не найден"));
        return new ArrayList<>(course.getStudents());
    }

    private CourseDto convertToDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        // Безопасно достаем имя категории для DTO
        if (course.getCategory() != null) {
            dto.setCategoryName(course.getCategory().getName());
        }
        return dto;
    }
}