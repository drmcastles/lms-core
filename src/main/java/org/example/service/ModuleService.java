package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.LessonDto;
import org.example.dto.ModuleDto;
import org.example.entity.Course;
import org.example.entity.Module;
import org.example.repository.CourseRepository;
import org.example.repository.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public ModuleDto createModule(Long courseId, ModuleDto dto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курс не найден"));

        Module module = new Module();
        module.setTitle(dto.getTitle());
        module.setCourse(course);

        Module saved = moduleRepository.save(module);
        return convertToDto(saved);
    }

    @Transactional(readOnly = true)
    public List<ModuleDto> getAllModules() {
        return moduleRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ModuleDto> getModulesByCourseId(Long courseId) {
        return moduleRepository.findByCourseId(courseId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }


    private ModuleDto convertToDto(Module module) {
        ModuleDto dto = new ModuleDto();
        dto.setId(module.getId());
        dto.setTitle(module.getTitle());

        if (module.getLessons() != null) {
            dto.setLessons(module.getLessons().stream().map(lesson -> {
                LessonDto lDto = new LessonDto();
                lDto.setId(lesson.getId());
                lDto.setTitle(lesson.getTitle());
                lDto.setContent(lesson.getContent());
                return lDto;
            }).collect(Collectors.toList()));
        }

        return dto;
    }
}