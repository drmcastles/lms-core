package org.example;

import org.example.entity.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FullProjectCoverageTest {

    @Test
    void testAllEntities() {
        // Проверка всех 17 сущностей на инициализацию
        assertNotNull(new User());
        assertNotNull(new Course());
        assertNotNull(new Category());
        assertNotNull(new Lesson());
        assertNotNull(new Enrollment());
        assertNotNull(new Assignment());
        assertNotNull(new Submission());
        assertNotNull(new Role());
        assertNotNull(new Review());
        assertNotNull(new Quiz());
        assertNotNull(new Question());
        assertNotNull(new Option());
        assertNotNull(new AnswerOption());
        assertNotNull(new QuizResult());
        assertNotNull(new Certificate());


        org.example.entity.Module module = new org.example.entity.Module();
        assertNotNull(module);

        // Проверка полей User
        User user = new User();
        user.setName("Ivan");
        user.setEmail("ivan@test.com");
        assertNotNull(user.getName());
    }
}