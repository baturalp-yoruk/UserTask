package com.example.usertask.model.converter;

import com.example.usertask.model.dto.UserDto;
import com.example.usertask.model.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static List<UserDto> convert(List<UserEntity> userEntities){
        return userEntities
                .stream()
                .map(UserConverter::convert)
                .collect(Collectors.toList());
    }

    public static UserDto convert(UserEntity userEntity){
        UserDto userDto = new UserDto();
        //userDTO.setPassword(userEntity.getPassword());
        userDto.setRole(userEntity.getRole());
        userDto.setUserName(userEntity.getUserName());
        userDto.setUserId(userEntity.getId());

        return userDto;
    }

}
