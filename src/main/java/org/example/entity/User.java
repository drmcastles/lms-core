package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email; // Проверь наличие
import jakarta.validation.constraints.NotBlank; // Проверь наличие
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя пользователя обязательно")
    private String name;

    @Email(message = "Некорректный формат email")
    @NotBlank(message = "Email обязателен")
    @Column(unique = true)
    private String email;

    private String role;
}