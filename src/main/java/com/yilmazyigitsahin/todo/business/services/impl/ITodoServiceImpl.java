package com.yilmazyigitsahin.todo.business.services.impl;

import com.yilmazyigitsahin.todo.business.dto.TodoDto;
import com.yilmazyigitsahin.todo.data.entity.TodoEntity;
import com.yilmazyigitsahin.todo.exception.TodoNotFoundException;
import com.yilmazyigitsahin.todo.repository.TodoRepository;
import com.yilmazyigitsahin.todo.business.services.intefaces.ITodoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ITodoServiceImpl implements ITodoService {
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        TodoEntity todo = modelMapper.map(todoDto, TodoEntity.class);
        TodoEntity savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<TodoEntity> todos = todoRepository.findAll();
        return todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto getTodoById(Long id) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        TodoEntity updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
}