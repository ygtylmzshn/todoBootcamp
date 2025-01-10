package com.yilmazyigitsahin.todo.business.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private boolean completed;
}