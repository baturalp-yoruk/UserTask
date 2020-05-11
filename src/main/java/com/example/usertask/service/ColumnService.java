package com.example.usertask.service;

import com.example.usertask.controller.request.CreateColumnRequest;
import com.example.usertask.model.dto.ColumnDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ColumnService {
    List<ColumnDto> columnList();
    void createColumn(CreateColumnRequest request);
}
