package com.kodigo.SpringBootProject.exception;

public class TodoCollectionException extends Exception{

    private static final long serialVersionUID = 1L;

    public TodoCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "ToDo with " + id + " not found!";
    }

    public static String TodoAlreadyExists() {
        return "ToDo with given name already exists";
    }
}
