package com.yilmazyigitsahin.todo.controller;

import com.yilmazyigitsahin.todo.business.dto.TodoDto;
import com.yilmazyigitsahin.todo.business.services.intefaces.ITodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final ITodoService ITodoService;

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@Valid @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(ITodoService.createTodo(todoDto));
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        return ResponseEntity.ok(ITodoService.getAllTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(ITodoService.getTodoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(ITodoService.updateTodo(id, todoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        ITodoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}