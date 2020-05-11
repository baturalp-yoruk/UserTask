package com.example.usertask.model.converter;

import com.example.usertask.model.dto.ColumnDto;
import com.example.usertask.model.entity.ColumnEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ColumnConverter {

    public static List<ColumnDto> convert(List<ColumnEntity> columnEntities){
        return columnEntities
                .stream()
                .map(ColumnConverter::convert)
                .collect(Collectors.toList());
    }

    public static ColumnDto convert(ColumnEntity columnEntity){
        ColumnDto columnDto = new ColumnDto();
        columnDto.setId(columnEntity.getId());
        columnDto.setName(columnEntity.getName());
        return columnDto;
    }

}
