package com.yilmazyigitsahin.todo.service;

import com.yilmazyigitsahin.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto createTodo(Long userId, TodoDto todoDto);
    List<TodoDto> getTodosByUserId(Long userId);
    TodoDto getTodoById(Long id);
    TodoDto updateTodo(Long id, TodoDto todoDto);
    void deleteTodo(Long id);
}
