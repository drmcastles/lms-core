package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne // Много модулей относятся к одному курсу
    private Course course;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL) // Один модуль — много уроков
    private List<Lesson> lessons;
}