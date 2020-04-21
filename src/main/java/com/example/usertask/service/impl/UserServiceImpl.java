package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateUserRequest;
import com.example.usertask.controller.request.UpdateUserRequest;
import com.example.usertask.model.converter.CreateUserRequestConverter;
import com.example.usertask.model.converter.UserConverter;
import com.example.usertask.model.dto.UserDto;
import com.example.usertask.model.entity.UserEntity;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.repositories.UserRepository;
import com.example.usertask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> userList() {
        List<UserEntity> userEntities = userRepository.findAll();
        return UserConverter.convert(userEntities);
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
        UserEntity updatedUser = userRepository.save(userEntity);

        return UserConverter.convert(updatedUser);
    }

    @Override
    public void deleteUser(int id)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUserNameByName(String username){
        if(username.length()<3){
            return Collections.emptyList();
        }
        else{
            List<UserDto> searchResults = userRepository.findAllByUserName(username);
            return searchResults;
        }
    }
    private List<String> getUserNameListFromUserEntityList(List<UserEntity> searchResultUserEntityList){
        return searchResultUserEntityList.stream().map(user -> user.getUserName()).collect(Collectors.toList());
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
