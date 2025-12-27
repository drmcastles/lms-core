package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        // Если роль не указана, по умолчанию ставим STUDENT
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("STUDENT");
        }
        return userRepository.save(user);
    }
    // Создать нового пользователя (Студента или Преподавателя)
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Получить список всех пользователей
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Получить пользователя по ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    // Удалить пользователя
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}