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

    // Связь с учителем (один пользователь может вести много курсов)
    @ManyToOne(fetch = FetchType.LAZY)
    private User teacher;

    // Связь с модулями (один курс — много модулей)
    // cascade = ALL значит, что если удалим курс, удалятся и его модули
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Module> modules;

    // Связь с записями студентов
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
}