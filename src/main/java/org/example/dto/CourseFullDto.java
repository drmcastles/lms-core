package org.example.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CourseFullDto extends CourseDto {
    private List<ModuleDto> modules; // Вложенная структура курса
}