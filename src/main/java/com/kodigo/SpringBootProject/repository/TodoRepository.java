package com.kodigo.SpringBootProject.repository;

import com.kodigo.SpringBootProject.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<ToDo, String> {

    @Query("{'title': ?0}")
    Optional<ToDo> findByTodo(String todo);
}
