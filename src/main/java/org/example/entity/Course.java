package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Module> modules;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    // Добавим связь с отзывами, чтобы потом не возвращаться
    @OneToMany(mappedBy = "course")
    private List<Review> reviews;
}