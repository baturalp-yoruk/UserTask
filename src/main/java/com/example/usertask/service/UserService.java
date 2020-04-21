package com.example.usertask.service;

import com.example.usertask.controller.request.CreateUserRequest;
import com.example.usertask.controller.request.UpdateUserRequest;
import com.example.usertask.model.dto.UserDto;
import com.example.usertask.model.entity.UserEntity;
import com.example.usertask.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    List<UserDto> userList();
    void createUser(CreateUserRequest request);
    UserDto getUserById(int id) throws UserNotFoundException;
    UserDto updateUser(int id, UpdateUserRequest updateUserRequest) throws UserNotFoundException;
    void deleteUser(int id) throws UserNotFoundException;
    List<UserDto> getUserNameByName(String username);
}
