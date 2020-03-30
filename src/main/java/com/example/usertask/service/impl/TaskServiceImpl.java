package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateTaskRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.converter.CreateTaskRequestConverter;
import com.example.usertask.model.converter.TaskConverter;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.model.entity.UserEntity;
import com.example.usertask.model.enums.TaskStatus;
import com.example.usertask.repositories.TaskRepository;
import com.example.usertask.repositories.UserRepository;
import com.example.usertask.service.TaskService;
import org.omg.CORBA.TCKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskServiceImpl")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TaskDto> taskList() {
        List<TaskEntity> taskEntities = taskRepository.findAll();
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

          return TaskConverter.convert(taskEntity);
    }

    @Override
    public TaskDto updateTask(int id, TaskEntity taskEntityDetails)  throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskEntity.setTaskName(taskEntityDetails.getTaskName());
        taskEntity.setEndDate(taskEntityDetails.getEndDate());
        taskEntity.setStartDate(taskEntityDetails.getStartDate());
        taskEntity.setUserEntity(taskEntityDetails.getUserEntity());
        taskEntity.setStatus((TaskStatus.DONE).toString());

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
    public void deleteTask(int id) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(taskEntity);
    }


}
