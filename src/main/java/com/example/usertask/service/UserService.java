package com.example.usertask.service;

import com.example.usertask.controller.request.CreateUserRequest;
import com.example.usertask.controller.request.UpdateUserRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserService {

    List<UserDto> userList();
    void createUser(CreateUserRequest request);
    UserDto getUserById(int id) throws UserNotFoundException;
    UserDto updateUser(int id, UpdateUserRequest updateUserRequest) throws UserNotFoundException;
    void deleteUser(int id) throws UserNotFoundException;
    List<UserDto> getUserNameByName(String username);
    Map<TaskDto, List<MetricDto>> getTasksMetrics(int userId);
}
