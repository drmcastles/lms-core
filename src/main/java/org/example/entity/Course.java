package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Для ошибки в App.java (нужен объект Category)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Для ошибки в CourseService.java (нужна строка)
    // Если сервис хочет сохранять именно строку напрямую в Course
    private String categoryName;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Module> modules = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;
}