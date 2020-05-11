package com.example.usertask.model.converter;

import com.example.usertask.controller.request.CreateColumnRequest;
import com.example.usertask.model.entity.ColumnEntity;

public class CreateColumnRequestConverter {
    public static ColumnEntity convert(CreateColumnRequest request){
        ColumnEntity columnEntity = new ColumnEntity();
        columnEntity.setName(request.getName());
        return columnEntity;
    }
}
