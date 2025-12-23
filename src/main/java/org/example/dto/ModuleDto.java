package org.example.dto;

import lombok.Data;
import java.util.List;

@Data
public class ModuleDto {
    private Long id;
    private String title;
    private List<LessonDto> lessons; // Вложенный список уроков
}