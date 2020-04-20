package com.example.usertask.service.impl;

import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.converter.MetricConverter;
import com.example.usertask.model.converter.TaskConverter;
import com.example.usertask.model.converter.UserConverter;
import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.dto.UserDto;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.model.entity.UserEntity;
import com.example.usertask.repositories.TaskRepository;
import com.example.usertask.repositories.UserRepository;
import com.example.usertask.service.SearchService;
import com.example.usertask.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<UserDto> searchUsers(String keyword) throws UserNotFoundException {
        List<UserEntity> userEntities;
        if(keyword.length()<3){
            return null;
        }
        else{
            userEntities = userRepository.findAll()
                    .stream().filter(userEntity -> userEntity.getUserName().startsWith(keyword)) .collect(Collectors.toList());
        }
        List<UserDto> userDtos = UserConverter.convert(userEntities);
        return userDtos;
    }

    @Override
    public HashMap<TaskDto, List<MetricDto>> getTasksMetrics(int userId) throws UserNotFoundException {

        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        List<TaskEntity> taskEntityList = taskRepository.findAll()
                .stream().filter(taskEntity -> taskEntity.getUserEntity().getId()==userId) .collect(Collectors.toList());

        HashMap<TaskDto, List<MetricDto>> hashMap = new HashMap<>();

        for(TaskEntity taskEntity: taskEntityList){
            hashMap.put(TaskConverter.convert(taskEntity), MetricConverter.convert(taskEntity.getMetricEntities()));
        }
        return hashMap;
    }
}
