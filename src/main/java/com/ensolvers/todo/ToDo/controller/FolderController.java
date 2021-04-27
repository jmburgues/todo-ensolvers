package com.ensolvers.todo.ToDo.controller;

import com.ensolvers.todo.ToDo.model.Folder;
import com.ensolvers.todo.ToDo.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folder")
public class FolderController {
    @Autowired
    private FolderService folderService;

    @GetMapping
    public List<Folder> getAll(){
        return this.folderService.getAll();
    }

    @GetMapping("{idFolder}")
    public Folder getById(@PathVariable Integer idFolder){
        return this.folderService.getById(idFolder);
    }

    @PostMapping
    public void add(@RequestBody Folder newFolder){
        this.folderService.add(newFolder);
    }

    @DeleteMapping("{idFolder}")
    public void delete(@PathVariable Integer idFolder){
        this.folderService.delete(idFolder);
    }

    @PutMapping
    public void update(@RequestBody Folder toUpdate){
        this.folderService.update(toUpdate);
    }

}
