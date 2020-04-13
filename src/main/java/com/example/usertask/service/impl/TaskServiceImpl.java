package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateMetricRequest;
import com.example.usertask.controller.request.CreateTaskRequest;
import com.example.usertask.controller.request.UpdateTaskRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.converter.CreateMetricRequestConverter;
import com.example.usertask.model.converter.CreateTaskRequestConverter;
import com.example.usertask.model.converter.TaskConverter;
import com.example.usertask.model.converter.UserEntityConverter;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MetricRepository metricRepository;

    @Override
    public List<TaskDto> taskList() {

        List<TaskEntity> taskEntities = taskRepository.findAll()
                .stream().filter(taskEntity -> !taskEntity.isDeleted()) .collect(Collectors.toList());
        
        return TaskConverter.convert(taskEntities);
    }

    @Override
    public void createTask(CreateTaskRequest request) {
        TaskEntity taskEntity = CreateTaskRequestConverter.convert(request);
        taskRepository.save(taskEntity);}

    @Override
    public TaskDto getTaskById(int id) throws TaskNotFoundException{
          TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

          if(taskEntity.isDeleted()){
              return null;
          }

          return TaskConverter.convert(taskEntity);
    }

    @Override
    public TaskDto updateTask(int id, UpdateTaskRequest updateTaskRequest) throws TaskNotFoundException, UserNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        UserDto userDto = userService.getUserById(updateTaskRequest.getUserId());
        UserEntity userEntity = UserEntityConverter.convert(userDto);
        prepareTaskEntity(updateTaskRequest, taskEntity, userEntity);
        TaskEntity updatedTask = taskRepository.save(taskEntity);

        return TaskConverter.convert(updatedTask);
    }

    @Override
    public TaskDto assignTask(int userid, int taskid) throws TaskNotFoundException, UserNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(taskid)
                .orElseThrow(() -> new TaskNotFoundException(taskid));

        UserEntity userEntity = userRepository.findById(userid)
                .orElseThrow(() -> new UserNotFoundException(userid));

        taskEntity.setUserEntity(userEntity);

        TaskEntity updatedTask = taskRepository.save(taskEntity);

        return TaskConverter.convert(updatedTask);
    }

    @Override
    public TaskDto deleteTask(int id) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskEntity.setDeleted(true);
        TaskEntity updatedTask = taskRepository.save(taskEntity);
        return TaskConverter.convert(updatedTask);
    }

    @Override
    public TaskDto assignMetric(int taskid, CreateMetricRequest createMetricRequest) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(taskid).orElseThrow(() -> new TaskNotFoundException(taskid));

        List<MetricEntity> metricEntities = Collections.singletonList(CreateMetricRequestConverter.convert(createMetricRequest));
        metricRepository.saveAll(metricEntities);

        taskEntity.setMetricEntities(metricEntities);

        TaskEntity updatedTask = taskRepository.save(taskEntity);

        return TaskConverter.convert(updatedTask);

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

}
