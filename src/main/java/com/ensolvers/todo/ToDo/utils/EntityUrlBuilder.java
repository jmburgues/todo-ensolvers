package com.ensolvers.todo.ToDo.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class EntityUrlBuilder {

    public static String buildURL(String entity, String id){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(("{entity}/{id}"))
                .buildAndExpand(entity,id)
                .toUriString();
    }

    public static String buildURL(String path){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(("{path}"))
                .buildAndExpand(path)
                .toUriString();
    }
}
