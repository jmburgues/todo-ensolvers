package com.ensolvers.todo.ToDo.service;

import com.ensolvers.todo.ToDo.model.Folder;
import com.ensolvers.todo.ToDo.model.PostResponse;
import com.ensolvers.todo.ToDo.model.Task;
import com.ensolvers.todo.ToDo.repository.FolderRepository;
import com.ensolvers.todo.ToDo.repository.TaskRepository;
import com.ensolvers.todo.ToDo.utils.EntityUrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {

    private static final String TASK_PATH = "task";
    private TaskRepository taskRepo;
    private FolderRepository folderRepo;

    @Autowired
    public TaskService(TaskRepository taskRepo, FolderRepository folderRepo) {
        this.taskRepo = taskRepo;
        this.folderRepo = folderRepo;
    }

    public List<Task> getAll(){
        List<Task> all = this.taskRepo.findAll();
        if(all.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There are no tasks created.");
        }
        return all;
    }

    public Task getById(Integer idTask){
        return this.taskRepo.findById(idTask)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task id: " + idTask + " doest not exists."));
    }

    public PostResponse add(Task newTask){
        Task saved = this.taskRepo.save(newTask);
        return PostResponse
                .builder()
                .status(HttpStatus.CREATED)
                .url(EntityUrlBuilder.buildURL(TASK_PATH,saved.getId().toString()))
                .build();
    }

    public void delete(Integer idTask){
        Task toDelete = this.taskRepo.findById(idTask)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task id: " + idTask + " doest not exists."));
        this.taskRepo.deleteById(idTask);
    }

    public PostResponse update(Task toEdit){
        this.getById(toEdit.getId());
        Task updated = this.taskRepo.save(toEdit);
        return PostResponse
                .builder()
                .status(HttpStatus.CREATED)
                .url(EntityUrlBuilder.buildURL(TASK_PATH,updated.getId().toString()))
                .build();
    }
}
