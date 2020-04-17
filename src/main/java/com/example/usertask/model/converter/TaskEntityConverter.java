package com.example.usertask.model.converter;

import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.entity.TaskEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TaskEntityConverter {

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
        taskEntity.setUserEntity(taskDto.getUserEntity());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setMetricEntities(taskDto.getMetricEntities());

        return taskEntity;
    }
}
