package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateUserRequest;
import com.example.usertask.controller.request.UpdateUserRequest;
import com.example.usertask.model.converter.CreateUserRequestConverter;
import com.example.usertask.model.converter.MetricConverter;
import com.example.usertask.model.converter.TaskConverter;
import com.example.usertask.model.converter.UserConverter;
import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.dto.UserDto;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.model.entity.UserEntity;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.repositories.TaskRepository;
import com.example.usertask.repositories.UserRepository;
import com.example.usertask.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final int FIRST_3_CHARACTER = 3;

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<UserDto> userList() {
        return UserConverter.convert(userRepository.findAll());
    }

    @Override
    public void createUser(CreateUserRequest request) {
        UserEntity userEntity = CreateUserRequestConverter.convert(request);
        userRepository.save(userEntity);
    }

    @Override
    public UserDto getUserById(int id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserConverter.convert(userEntity);
    }

    @Override
    public UserDto updateUser(int id, UpdateUserRequest updateUserRequest)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        prepareUserEntity(updateUserRequest, userEntity);

        return UserConverter.convert(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(int id)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUserNameByName(String username){
        if(username.length()<FIRST_3_CHARACTER)
            return Collections.emptyList();
        return UserConverter.convert(userRepository.findAllByUserName(username));
    }

    @Override
    public Map<TaskDto, List<MetricDto>> getTasksMetrics(int userId){
        if(!userRepository.findById(userId).isPresent()){
            return Collections.emptyMap();
        }
        //TODO: Check whether it works
        List<TaskEntity> taskEntityList = taskRepository.findAll()
                .stream().filter(taskEntity -> taskEntity.getUserEntity().getId()==userId).collect(Collectors.toList());

        Map<TaskDto, List<MetricDto>> taskMetricMap = new HashMap<>();
        for(TaskEntity taskEntity: taskEntityList){
            taskMetricMap.put(TaskConverter.convert(taskEntity), MetricConverter.convert(taskEntity.getMetricEntities()));
        }
        return taskMetricMap;
    }

    private void prepareUserEntity(UpdateUserRequest request, UserEntity userEntity) {
        if(request.getUserName() != null){
            userEntity.setUserName(request.getUserName());
        }
        if(request.getRole() != null){
            userEntity.setRole(request.getRole());
        }
        if(request.getPassword() != null){
            userEntity.setPassword(request.getPassword());
        }
    }
}
