package com.example.usertask.exception;

public class TaskNotFoundException extends Exception{
    private int id;
    public TaskNotFoundException(int id) {
        super(String.format("TaskEntity is not found with id : '%d'", id));
    }
}

