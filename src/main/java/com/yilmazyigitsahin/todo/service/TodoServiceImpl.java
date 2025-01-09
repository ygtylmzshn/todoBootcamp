package com.yilmazyigitsahin.todo.service;

import com.yilmazyigitsahin.todo.dto.TodoDto;
import com.yilmazyigitsahin.todo.entity.TodoEntity;
import com.yilmazyigitsahin.todo.entity.UserEntity;
import com.yilmazyigitsahin.todo.exception.TodoNotFoundException;
import com.yilmazyigitsahin.todo.exception.UserNotFoundException;
import com.yilmazyigitsahin.todo.repository.TodoRepository;
import com.yilmazyigitsahin.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public TodoDto createTodo(Long userId, TodoDto todoDto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        TodoEntity todo = modelMapper.map(todoDto, TodoEntity.class);
        todo.setUser(user);
        TodoEntity savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getTodosByUserId(Long userId) {
        List<TodoEntity> todos = todoRepository.findByUserId(userId);
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

        modelMapper.map(todoDto, todo);
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
