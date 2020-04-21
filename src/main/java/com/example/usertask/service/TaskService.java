package com.example.usertask.service;

import com.example.usertask.controller.request.CreateMetricRequest;
import com.example.usertask.controller.request.CreateTaskRequest;
import com.example.usertask.controller.request.UpdateTaskRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.model.entity.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {
    List<TaskDto> taskList();
    void createTask(CreateTaskRequest request);
    TaskDto getTaskById(int id) throws TaskNotFoundException;
    TaskDto updateTask(int id, UpdateTaskRequest updateTaskRequest) throws TaskNotFoundException, UserNotFoundException;
    TaskDto deleteTask(int id) throws TaskNotFoundException;
    TaskDto assignTask(int userid, int taskid) throws TaskNotFoundException, UserNotFoundException;
    TaskDto assignMetric(int taskid, CreateMetricRequest createMetricRequest) throws TaskNotFoundException;
    List<TaskEntity> findTaskList(List<Integer> taskIdList) throws TaskNotFoundException;
}
