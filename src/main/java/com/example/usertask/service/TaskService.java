package com.example.usertask.service;

import com.example.usertask.controller.request.CreateTaskRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.exception.TaskNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {
    List<TaskDto> taskList();
    void createTask(CreateTaskRequest request);
    TaskDto getTaskById(int id) throws TaskNotFoundException;
    TaskDto updateTask(int id, TaskEntity taskEntityDetails) throws TaskNotFoundException;
    void deleteTask(int id) throws TaskNotFoundException;
    TaskDto assignTask(int userid, int taskid) throws TaskNotFoundException, UserNotFoundException;

}
