package com.ensolvers.todo.ToDo.controller;

import com.ensolvers.todo.ToDo.model.PostResponse;
import com.ensolvers.todo.ToDo.model.Task;
import com.ensolvers.todo.ToDo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAll(){
        return this.taskService.getAll();
    }

    @GetMapping("{idTask}")
    public Task getById(@PathVariable Integer idTask){
        return this.taskService.getById(idTask);
    }

    @PostMapping
    public PostResponse add(@RequestBody Task newTask){
        return this.taskService.add(newTask);
    }

    @DeleteMapping("{idTask}")
    public void delete(@PathVariable Integer idTask){
        this.taskService.delete(idTask);
    }

    @PutMapping
    public PostResponse update(@RequestBody Task toUpdate){
        return this.taskService.update(toUpdate);
    }
}
