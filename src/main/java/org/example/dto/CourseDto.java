package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseDto {
    private Long id;

    @NotBlank(message = "Название курса обязательно")
    @Size(min = 3, max = 100, message = "Название должно быть от 3 до 100 символов")
    private String title;

    @NotBlank(message = "Описание обязательно")
    private String description;

    private String categoryName;
    private Long teacherId;
}