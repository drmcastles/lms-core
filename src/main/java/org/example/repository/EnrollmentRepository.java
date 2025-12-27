package org.example.repository;

import org.example.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Проверка дубликатов
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    // Поиск всех курсов конкретного студента
    List<Enrollment> findByStudentId(Long studentId);

    // Поиск всех студентов конкретного курса
    List<Enrollment> findByCourseId(Long courseId);
}