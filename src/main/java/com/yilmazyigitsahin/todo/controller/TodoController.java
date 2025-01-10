package com.yilmazyigitsahin.todo.controller;

import com.yilmazyigitsahin.todo.business.dto.TodoDto;
import com.yilmazyigitsahin.todo.business.services.intefaces.ITodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/todos")
@RequiredArgsConstructor
public class TodoController {
    private final ITodoService ITodoService;

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(
            @PathVariable Long userId,
            @Valid @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(ITodoService.createTodo(userId, todoDto));
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getTodos(@PathVariable Long userId) {
        return ResponseEntity.ok(ITodoService.getTodosByUserId(userId));
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto> getTodoById(
            @PathVariable Long userId,
            @PathVariable Long todoId) {
        return ResponseEntity.ok(ITodoService.getTodoById(userId, todoId));
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto> updateTodo(
            @PathVariable Long userId,
            @PathVariable Long todoId,
            @Valid @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(ITodoService.updateTodo(userId, todoId, todoDto));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long userId,
            @PathVariable Long todoId) {
        ITodoService.deleteTodo(userId, todoId);
        return ResponseEntity.noContent().build();
    }
}
