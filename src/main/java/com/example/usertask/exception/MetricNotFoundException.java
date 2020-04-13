package com.example.usertask.exception;

public class MetricNotFoundException extends Exception {
    private int id;

    public MetricNotFoundException(int id) {
        super(String.format("MetricEntity is not found with id : '%d'", id));
    }

}
