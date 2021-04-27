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
public class FolderService {

    private static final String FOLDER_PATH = "folder";
    private TaskRepository taskRepo;
    private FolderRepository folderRepo;

    @Autowired
    public FolderService(TaskRepository taskRepo, FolderRepository folderRepo) {
        this.taskRepo = taskRepo;
        this.folderRepo = folderRepo;
    }

    public List<Folder> getAll(){
        List<Folder> all = this.folderRepo.findAll();
        if(all.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There are no Folders created.");
        }
        return all;
    }

    public Folder getById(Integer idFolder){
        return this.folderRepo.findById(idFolder)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder id: " + idFolder + "does not exists."));
    }

    public PostResponse add(Folder newFolder){
        Folder saved = this.folderRepo.save(newFolder);
        return PostResponse
                .builder()
                .status(HttpStatus.CREATED)
                .url(EntityUrlBuilder.buildURL(FOLDER_PATH,saved.getId().toString()))
                .build();
    }

    public PostResponse update(Folder toEdit){
        this.getById(toEdit.getId());
        Folder edited = this.folderRepo.save(toEdit);
        return PostResponse
                .builder()
                .status(HttpStatus.CREATED)
                .url(EntityUrlBuilder.buildURL(FOLDER_PATH,edited.getId().toString()))
                .build();
    }

    public void delete(Integer idFolder){
        Folder toDelete = this.folderRepo.findById(idFolder)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder id: " + idFolder + "does not exists."));
        this.folderRepo.deleteById(idFolder);
    }

    public PostResponse addToList(Integer idFolder, Integer idTask){
        Task task = this.taskRepo.findById(idTask)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Folder folder = this.getById(idFolder);
        if(folder.getTasks().contains(task)){
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Task id: " + idTask + " already exists in Folder id: " + idFolder);
        }
        else{
            folder.getTasks().add(task);
            Folder updated = this.folderRepo.save(folder);
            return PostResponse
                    .builder()
                    .status(HttpStatus.CREATED)
                    .url(EntityUrlBuilder.buildURL(FOLDER_PATH+"/"+updated.getId().toString()+"/task/"+task.getId().toString()))
                    .build();
        }
    }
}
