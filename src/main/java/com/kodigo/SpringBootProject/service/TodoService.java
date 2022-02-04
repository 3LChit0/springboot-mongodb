package com.kodigo.SpringBootProject.service;

import com.kodigo.SpringBootProject.exception.TodoCollectionException;
import com.kodigo.SpringBootProject.model.ToDo;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface TodoService {

    public void createTodo(ToDo todo) throws ConstraintViolationException, TodoCollectionException;
    
    public List<ToDo> getAllTodos();

    public ToDo getSingleTodo(String id) throws TodoCollectionException;

    public void updateTodo(String id, ToDo todo) throws TodoCollectionException;

    public void deleteTodoById(String id) throws TodoCollectionException;
}
