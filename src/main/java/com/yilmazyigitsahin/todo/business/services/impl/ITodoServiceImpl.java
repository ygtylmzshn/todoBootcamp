package com.yilmazyigitsahin.todo.business.services.impl;

import com.yilmazyigitsahin.todo.business.dto.TodoDto;
import com.yilmazyigitsahin.todo.data.entity.TodoEntity;
import com.yilmazyigitsahin.todo.data.entity.UserEntity;
import com.yilmazyigitsahin.todo.exception.TodoNotFoundException;
import com.yilmazyigitsahin.todo.exception.UserNotFoundException;
import com.yilmazyigitsahin.todo.repository.TodoRepository;
import com.yilmazyigitsahin.todo.repository.UserRepository;
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
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        List<TodoEntity> todos = todoRepository.findByUserId(userId);
        return todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto getTodoById(Long userId, Long todoId) {
        TodoEntity todo = todoRepository.findById(todoId)
                .filter(t -> t.getUser().getId().equals(userId))
                .orElseThrow(() -> new TodoNotFoundException("Todo not found for this user: " + todoId));

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public TodoDto updateTodo(Long userId, Long todoId, TodoDto todoDto) {
        TodoEntity todo = todoRepository.findById(todoId)
                .filter(t -> t.getUser().getId().equals(userId))
                .orElseThrow(() -> new TodoNotFoundException("Todo not found for this user: " + todoId));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        TodoEntity updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long userId, Long todoId) {
        TodoEntity todo = todoRepository.findById(todoId)
                .filter(t -> t.getUser().getId().equals(userId))
                .orElseThrow(() -> new TodoNotFoundException("Todo not found for this user: " + todoId));

        todoRepository.delete(todo);
    }
}
