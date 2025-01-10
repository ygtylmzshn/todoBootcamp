package com.yilmazyigitsahin.todo.business.services.intefaces;

import com.yilmazyigitsahin.todo.business.dto.TodoDto;

import java.util.List;

public interface ITodoService {
    TodoDto createTodo(Long userId, TodoDto todoDto);
    List<TodoDto> getTodosByUserId(Long userId);
    TodoDto getTodoById(Long userId, Long todoId);
    TodoDto updateTodo(Long userId, Long todoId, TodoDto todoDto);
    void deleteTodo(Long userId, Long todoId);
}