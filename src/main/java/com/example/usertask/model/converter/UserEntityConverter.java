package com.example.usertask.model.converter;

import com.example.usertask.model.dto.UserDto;
import com.example.usertask.model.entity.UserEntity;
public class UserEntityConverter {

    public static UserEntity convert(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getUserId());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setRole(userDto.getRole());

        return userEntity;
    }

}
