package com.example.usertask.exception;

public class UserNotFoundException extends Exception{
    private int id;
    public UserNotFoundException(int id) {
        super(String.format("UserEntity is not found with id : '%d'", id));
    }
}
