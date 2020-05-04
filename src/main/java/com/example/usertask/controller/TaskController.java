package com.example.usertask.controller;

import com.example.usertask.controller.request.CreateMetricRequest;
import com.example.usertask.controller.request.CreateTaskRequest;
import com.example.usertask.controller.request.UpdateTaskRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<TaskDto> taskList(){
        return taskService.taskList();
    }

    @PostMapping("/tasks")
    public void createTask(@RequestBody CreateTaskRequest request) {
        taskService.createTask(request);
    }

    @GetMapping("/tasks/{id}")
    public TaskDto getTaskById(@PathVariable(value = "id") int id) throws TaskNotFoundException {
        return taskService.getTaskById(id);
    }


    @PutMapping("/tasks/{id}")
    public TaskDto updateTask(@PathVariable(value = "id") int id, @Valid @RequestBody UpdateTaskRequest updateTaskRequest)
            throws TaskNotFoundException, UserNotFoundException {
        return taskService.updateTask(id, updateTaskRequest);
    }

    @PutMapping("/tasks/{userId}/{taskId}")
    public TaskDto assignTask(@PathVariable(value = "userId") int userId,@PathVariable(value = "taskId") int taskId) throws TaskNotFoundException, UserNotFoundException {
        return taskService.assignTask(userId, taskId);
    }

    @PutMapping("/tasks/assignMetric/{taskId}")
    public TaskDto assignMetric(@PathVariable(value = "taskId") int taskId, CreateMetricRequest createMetricRequest) throws TaskNotFoundException{
        return taskService.assignMetric(taskId, createMetricRequest);
    }


    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable(value = "id") int id) throws TaskNotFoundException {
        taskService.deleteTask(id);
    }
    
}
