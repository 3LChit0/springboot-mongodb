package com.kodigo.SpringBootProject.service;

import com.kodigo.SpringBootProject.exception.TodoCollectionException;
import com.kodigo.SpringBootProject.model.ToDo;
import com.kodigo.SpringBootProject.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepo;

    @Override
    public void createTodo(ToDo todo) throws ConstraintViolationException, TodoCollectionException {
        Optional<ToDo> todoOptional = todoRepo.findByTodo(todo.getTitle());
        if (todoOptional.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        } else {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todo);
        }
    }

    @Override
    public List<ToDo> getAllTodos() {
        List<ToDo> toDos = todoRepo.findAll();
        if (toDos.size() > 0) {
            return toDos;
        } else {
            return new ArrayList<ToDo>();
        }
    }

    @Override
    public ToDo getSingleTodo(String id) throws TodoCollectionException {
        Optional<ToDo> optionalToDo = todoRepo.findById(id);
        if (!optionalToDo.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        } else {
            return optionalToDo.get();
        }
    }

    @Override
    public void updateTodo(String id, ToDo todo) throws TodoCollectionException {
        Optional<ToDo> todoWithId = todoRepo.findById(id);
        Optional<ToDo> todoWithSameName = todoRepo.findByTodo(todo.getTitle());
        if (todoWithId.isPresent()) {

            if (todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id)) {
                throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
            }

            ToDo todoToUpdate = todoWithId.get();
            todoToUpdate.setTitle(todo.getTitle());
            todoToUpdate.setDescription(todo.getDescription());
            todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todoToUpdate);
        } else {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteTodoById(String id) throws TodoCollectionException {
        Optional<ToDo> toDoOptional = todoRepo.findById(id);
        if (!toDoOptional.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        } else {
            todoRepo.deleteById(id);
        }
    }
}
