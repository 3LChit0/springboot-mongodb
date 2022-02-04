package com.kodigo.SpringBootProject.controller;

import com.kodigo.SpringBootProject.exception.TodoCollectionException;
import com.kodigo.SpringBootProject.model.ToDo;
import com.kodigo.SpringBootProject.repository.TodoRepository;
import com.kodigo.SpringBootProject.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepo;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {
        List<ToDo> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody ToDo todo){
        try {
            todoService.createTodo(todo);
            return new ResponseEntity<ToDo>(todo, HttpStatus.OK);
        } catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody ToDo todo){
        try {
            todoService.updateTodo(id, todo);
            return new ResponseEntity<>("Update ToDo with id " + id, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){
        try {
            todoService.deleteTodoById(id);
            return new ResponseEntity<>("Successfully deleted with id " + id, HttpStatus.OK);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
