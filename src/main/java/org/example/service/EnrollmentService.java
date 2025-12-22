package org.example.service;

import org.example.dto.EnrollmentResponse;
import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.User;
import org.example.repository.CourseRepository;
import org.example.repository.EnrollmentRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public EnrollmentResponse enrollStudentSecure(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);
        return new EnrollmentResponse(student.getName(), course.getTitle(), "Success");
    }

    // УБЕДИСЬ, ЧТО ЭТОТ МЕТОД ЕСТЬ:
    @Transactional
    public Enrollment enrollStudentEntity(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
    }
}