package com.ensolvers.todo.ToDo.repository;

import com.ensolvers.todo.ToDo.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Integer> {
}
