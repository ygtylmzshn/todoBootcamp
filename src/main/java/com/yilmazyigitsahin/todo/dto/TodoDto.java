package com.yilmazyigitsahin.todo.dto;

import lombok.Data;

@Data
public class TodoDto {
    private String title;
    private String description;
    private boolean completed;
}
