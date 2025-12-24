package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void smokeTestApi() throws Exception {
        // Проверяем Swagger (он затронет много внутренних методов)
        mockMvc.perform(get("/swagger-ui/index.html")).andExpect(status().isOk());

        // Добавь сюда GET запросы к своим основным контроллерам
        // Например, если есть контроллер курсов:
        mockMvc.perform(get("/api/courses")).andExpect(status().isOk());
    }
}