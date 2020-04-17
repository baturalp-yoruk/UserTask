package com.example.usertask.model.converter;

import com.example.usertask.controller.request.CreateProcessRequest;
import com.example.usertask.model.entity.ProcessEntity;
import org.apache.catalina.User;

public class CreateProcessRequestConverter {
    public static ProcessEntity convert(CreateProcessRequest request){
        ProcessEntity processEntity = new ProcessEntity();
        processEntity.setProcessName(request.getProcessName());
        processEntity.setStartDate(request.getStartDate());
        processEntity.setEndDate(request.getEndDate());
        processEntity.setStatus(request.getStatus());
        processEntity.setDeleted(request.isDeleted());
        processEntity.setUserEntity(UserEntityConverter.convert(request.getUserDto()));
        processEntity.setTaskEntities(TaskEntityConverter.convert(request.getTaskDtos()));

        return processEntity;
    }
}
