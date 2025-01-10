package com.yilmazyigitsahin.todo.business.services.intefaces;

import com.yilmazyigitsahin.todo.business.dto.TodoDto;

import java.util.List;

public interface ITodoService {
    TodoDto createTodo(TodoDto todoDto);
    List<TodoDto> getAllTodos();
    TodoDto getTodoById(Long id);
    TodoDto updateTodo(Long id, TodoDto todoDto);
    void deleteTodo(Long id);
}