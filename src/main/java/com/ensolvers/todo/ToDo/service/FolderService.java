package com.ensolvers.todo.ToDo.service;

import com.ensolvers.todo.ToDo.model.Folder;
import com.ensolvers.todo.ToDo.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class FolderService {
    @Autowired
    private FolderRepository folderRepo;

    public List<Folder> getAll(){
        List<Folder> all = this.folderRepo.findAll();
        if(all.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return all;
    }

    public Folder getById(Integer idFolder){
        return this.folderRepo.findById(idFolder)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void add(Folder newFolder){
        this.folderRepo.save(newFolder);
    }

    public void delete(Integer idFolder){
        Folder toDelete = this.folderRepo.findById(idFolder)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        this.folderRepo.deleteById(idFolder);
    }
}
