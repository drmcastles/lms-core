package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String role; // STUDENT, TEACHER, ADMIN

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore // Чтобы при выводе юзера не тянуть список всех его курсов
    private List<Course> teacherCourses;

    @ManyToMany(mappedBy = "students")
    @JsonIgnore // Чтобы не было цикла Студент -> Курс -> Студент
    private List<Course> courses;
}