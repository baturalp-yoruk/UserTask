package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateColumnRequest;
import com.example.usertask.model.converter.ColumnConverter;
import com.example.usertask.model.converter.CreateColumnRequestConverter;
import com.example.usertask.model.dto.ColumnDto;
import com.example.usertask.repositories.ColumnRepository;
import com.example.usertask.service.ColumnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {
    private final ColumnRepository columnRepository;

    public ColumnServiceImpl(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    @Override
    public List<ColumnDto> columnList() {
        return ColumnConverter.convert(columnRepository.findAll());
    }

    @Override
    public void createColumn(CreateColumnRequest request) {
        columnRepository.save(CreateColumnRequestConverter.convert(request));
    }
}
