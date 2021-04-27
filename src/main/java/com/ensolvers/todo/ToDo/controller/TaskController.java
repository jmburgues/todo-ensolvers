package com.ensolvers.todo.ToDo.controller;

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
    public void add(@RequestBody Task newTask){
        this.taskService.add(newTask);
    }

    @DeleteMapping("{idTask}")
    public void delete(@PathVariable Integer idTask){
        this.taskService.delete(idTask);
    }

    @PutMapping
    public void update(@RequestBody Task toUpdate){
        this.taskService.update(toUpdate);
    }

    @PutMapping("{idTask}/folder/{idFolder}")
    public void addToFolder(@PathVariable Integer idTask, @PathVariable Integer idFolder){
        this.taskService.addToFolder(idTask,idFolder);
    }
}
