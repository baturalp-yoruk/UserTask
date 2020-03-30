package com.example.usertask.exception;

public class ProcessNotFoundException extends Exception {
    private int id;
    public ProcessNotFoundException(int id) {
        super(String.format("ProcessEntity is not found with id : '%d'", id));
    }

}
