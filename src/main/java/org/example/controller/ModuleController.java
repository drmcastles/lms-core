package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ModuleDto;
import org.example.service.ModuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    // POST с PathVariable (уже работает)
    @PostMapping("/course/{courseId}")
    public ModuleDto create(@PathVariable Long courseId, @RequestBody ModuleDto dto) {
        return moduleService.createModule(courseId, dto);
    }

    // GET с опциональным параметром (в Swagger будет поле courseId)
    @GetMapping
    public List<ModuleDto> getAll(@RequestParam(required = false) Long courseId) {
        if (courseId != null) {
            return moduleService.getModulesByCourseId(courseId);
        }
        return moduleService.getAllModules();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        moduleService.deleteModule(id);
    }
}