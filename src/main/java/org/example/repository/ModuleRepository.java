package org.example.repository;

import org.example.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    // Этот метод позволит фильтровать модули по курсу
    List<Module> findByCourseId(Long courseId);
}