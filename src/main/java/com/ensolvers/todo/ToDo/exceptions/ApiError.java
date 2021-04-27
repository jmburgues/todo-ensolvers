package com.ensolvers.todo.ToDo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    HttpStatus httpStatus;
    String message;
    List<String> errors;

}