package com.ensolvers.todo.ToDo.repository;

import com.ensolvers.todo.ToDo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
}
