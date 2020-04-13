package com.example.usertask.model.converter;

import com.example.usertask.controller.request.CreateTaskRequest;
import com.example.usertask.model.entity.TaskEntity;

public class CreateTaskRequestConverter {
    public static TaskEntity convert(CreateTaskRequest request){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName(request.getTaskName());
        taskEntity.setStartDate(request.getStartDate());
        taskEntity.setEndDate(request.getEndDate());
        taskEntity.setStatus(request.getStatus());
        taskEntity.setDeleted(request.isDeleted());
        taskEntity.setUserEntity(request.getUserEntity());
        taskEntity.setDescription(request.getDescription());
        taskEntity.setMetricEntities(request.getMetricEntities());

        return taskEntity;
    }
}
