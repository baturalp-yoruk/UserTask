package com.example.usertask.model.converter;

import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.dto.UserDto;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TaskEntityConverter {

    @Autowired
    private static UserRepository userRepository;

    public static List<TaskEntity> convert(List<TaskDto> taskDtoList){
        return taskDtoList
                .stream()
                .map(TaskEntityConverter::convert)
                .collect(Collectors.toList());
    }

    public static TaskEntity convert(TaskDto taskDto){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName(taskDto.getTaskName());
        taskEntity.setStartDate(taskDto.getStartDate());
        taskEntity.setEndDate(taskDto.getEndDate());
        taskEntity.setStatus(taskDto.getStatus());
        taskEntity.setId(taskDto.getTaskId());
        taskEntity.setDeleted(taskDto.isDeleted());
        int id = taskDto.getUserId();
        UserDto userDto = null;
        try {
            userDto = UserConverter.convert(userRepository.findById(id)
                    .orElseThrow(()-> new UserNotFoundException(id)));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        taskEntity.setUserEntity(UserEntityConverter.convert(userDto));

        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setMetricEntities(MetricEntityConverter.convert(taskDto.getMetricDtoList()));

        return taskEntity;
    }
}
