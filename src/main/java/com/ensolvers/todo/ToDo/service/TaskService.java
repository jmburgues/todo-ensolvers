package com.ensolvers.todo.ToDo.service;

import com.ensolvers.todo.ToDo.model.Folder;
import com.ensolvers.todo.ToDo.model.Task;
import com.ensolvers.todo.ToDo.repository.FolderRepository;
import com.ensolvers.todo.ToDo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class TaskService {

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
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return all;
    }

    public Task getById(Integer idTask){
        return this.taskRepo.findById(idTask)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void add(Task newTask){
        this.taskRepo.save(newTask);
    }

    public void delete(Integer idTask){
        Task toDelete = this.taskRepo.findById(idTask)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        this.taskRepo.deleteById(idTask);
    }

    public void update(Task toEdit){
        this.getById(toEdit.getId());
        this.taskRepo.save(toEdit);

    }

    public void addToFolder(Integer idTask, Integer idFolder){
        Folder folder = this.folderRepo.findById(idFolder)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Task task = this.getById(idTask);
        if(folder.getTasks().contains(task)){
            throw new HttpClientErrorException(HttpStatus.ALREADY_REPORTED);
        }
        else{
            folder.getTasks().add(task);
            this.folderRepo.save(folder);
        }
    }
}
