package com.example.usertask.controller.request;

import com.example.usertask.model.enums.ProcessStatus;
import com.example.usertask.model.enums.TaskStatus;

public class UpdateTaskRequest {

    private String taskName;
    private String description;
    private TaskStatus taskStatus;
    private int userId;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
