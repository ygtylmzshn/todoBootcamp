package com.yilmazyigitsahin.todo.repository;

import com.yilmazyigitsahin.todo.data.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}