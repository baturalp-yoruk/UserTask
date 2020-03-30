package com.example.usertask.model.converter;

import com.example.usertask.controller.request.CreateUserRequest;
import com.example.usertask.model.entity.UserEntity;

public class CreateUserRequestConverter {
    public static UserEntity convert(CreateUserRequest request){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(request.getPassword());
        userEntity.setRole(request.getRole());
        userEntity.setUserName(request.getUserName());
        return userEntity;
    }

}
