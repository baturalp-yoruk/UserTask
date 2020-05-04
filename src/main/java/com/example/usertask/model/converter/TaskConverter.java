package com.example.usertask.model.converter;

import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.entity.TaskEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TaskConverter {

    public static List<TaskDto> convert(List<TaskEntity> taskEntityList){
        return taskEntityList
                .stream()
                .map(TaskConverter::convert)
                .collect(Collectors.toList());
    }

    public static TaskDto convert(TaskEntity taskEntity){
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskName(taskEntity.getTaskName());
        taskDto.setStartDate(taskEntity.getStartDate());
        taskDto.setEndDate(taskEntity.getEndDate());
        taskDto.setStatus(taskEntity.getStatus());
        taskDto.setTaskId(taskEntity.getId());
        taskDto.setDeleted(taskEntity.isDeleted());
        taskDto.setUserId(taskEntity.getUserEntity().getId());
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setMetricDtoList(MetricConverter.convert(taskEntity.getMetricEntities()));

        return taskDto;
    }
}
