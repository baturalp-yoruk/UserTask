package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateMetricRequest;
import com.example.usertask.controller.request.CreateTaskRequest;
import com.example.usertask.controller.request.UpdateTaskRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.converter.*;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.dto.UserDto;
import com.example.usertask.model.entity.MetricEntity;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.model.entity.UserEntity;
import com.example.usertask.repositories.MetricRepository;
import com.example.usertask.repositories.TaskRepository;
import com.example.usertask.repositories.UserRepository;
import com.example.usertask.service.TaskService;
import com.example.usertask.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final MetricRepository metricRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository,
                           UserService userService, MetricRepository metricRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.metricRepository = metricRepository;
    }

    @Override
    public List<TaskDto> taskList() {
        return TaskConverter.convert(getNotDeletedTaskEntities());
    }

    @Override
    public void createTask(CreateTaskRequest request) {
        TaskEntity taskEntity = CreateTaskRequestConverter.convert(request);
        taskRepository.save(taskEntity);
    }

    @Override
    public TaskDto getTaskById(int id) throws TaskNotFoundException{
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return taskEntity.isDeleted() ? null : TaskConverter.convert(taskEntity);
    }

    @Override
    public TaskDto updateTask(int id, UpdateTaskRequest updateTaskRequest) throws TaskNotFoundException, UserNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        UserEntity userEntity = UserEntityConverter.convert(userService.getUserById(updateTaskRequest.getUserId()));
        prepareTaskEntity(updateTaskRequest, taskEntity, userEntity);
        return TaskConverter.convert(taskRepository.save(taskEntity));
    }

    @Override
    public TaskDto assignTask(int userId, int taskId) throws TaskNotFoundException, UserNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        taskEntity.setUserEntity(userEntity);

        return TaskConverter.convert(taskRepository.save(taskEntity));
    }

    @Override
    public TaskDto deleteTask(int id) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskEntity.setDeleted(true);
        return TaskConverter.convert(taskRepository.save(taskEntity));
    }

    @Override
    public TaskDto assignMetric(int taskId, CreateMetricRequest createMetricRequest) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

        List<MetricEntity> metricEntities = MetricEntityConverter.convert(createMetricRequest.getMetrics());
        metricRepository.saveAll(metricEntities);

        taskEntity.setMetricEntities(metricEntities);

        setMetricsTask(taskEntity);

        return TaskConverter.convert(taskRepository.save(taskEntity));

    }

    @Override
    public List<TaskEntity> findTaskList(List<Integer> taskIdList) throws TaskNotFoundException {
        List<TaskEntity> taskEntities = new ArrayList<>();

        for(Integer id: taskIdList){
            taskEntities.add(taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id)));
        }
        return taskEntities;
    }

    private void prepareTaskEntity(UpdateTaskRequest request, TaskEntity taskEntity, UserEntity userEntity) {

        if(request.getUserId() != 0){
            taskEntity.setUserEntity(userEntity);
        }
        if(request.getTaskName() != null){
            taskEntity.setTaskName(request.getTaskName());
        }
        if(request.getTaskStatus() != null){
            taskEntity.setStatus(String.valueOf(request.getTaskStatus()));
        }
        if(request.getDescription() != null){
            taskEntity.setDescription(request.getDescription());
        }
    }

    private List<TaskEntity> getNotDeletedTaskEntities() {
        return taskRepository.findAll()
                .stream().filter(taskEntity -> !taskEntity.isDeleted()).collect(Collectors.toList());
    }

    private void setMetricsTask(TaskEntity taskEntity) {
        List<MetricEntity> metricEntity = taskEntity.getMetricEntities();
        metricEntity.stream().forEach(metricEntity1->metricEntity1.setTaskEntity(taskEntity));
    }

}
