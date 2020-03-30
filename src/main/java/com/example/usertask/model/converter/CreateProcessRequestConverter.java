package com.example.usertask.model.converter;

import com.example.usertask.controller.request.CreateProcessRequest;
import com.example.usertask.model.entity.ProcessEntity;

public class CreateProcessRequestConverter {
    public static ProcessEntity convert(CreateProcessRequest request){
        ProcessEntity processEntity = new ProcessEntity();
        processEntity.setProcessName(request.getProcessName());
        processEntity.setStartDate(request.getStartDate());
        processEntity.setEndDate(request.getEndDate());
        processEntity.setStatus(request.getStatus());

        return processEntity;
    }
}
