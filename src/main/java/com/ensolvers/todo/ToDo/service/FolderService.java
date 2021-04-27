package com.ensolvers.todo.ToDo.service;

import com.ensolvers.todo.ToDo.model.Folder;
import com.ensolvers.todo.ToDo.model.Task;
import com.ensolvers.todo.ToDo.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FolderService {
    @Autowired
    private FolderRepository folderRepo;

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

    public void add(Folder newFolder){
        this.folderRepo.save(newFolder);
    }

    public void update(Folder toEdit){
        this.getById(toEdit.getId());
        this.folderRepo.save(toEdit);

    }

    public void delete(Integer idFolder){
        Folder toDelete = this.folderRepo.findById(idFolder)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder id: " + idFolder + "does not exists."));
        this.folderRepo.deleteById(idFolder);
    }
}
