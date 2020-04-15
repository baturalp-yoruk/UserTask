package com.example.usertask.model.converter;

import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.entity.MetricEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MetricEntityConverter {

    public static MetricEntity convert(MetricDto metricDto){
        MetricEntity metricEntity = new MetricEntity();

        metricEntity.setId(metricEntity.getId());
        metricEntity.setStartDate(metricEntity.getStartDate());
        metricEntity.setOriginalEndDate(metricEntity.getOriginalEndDate());
        metricEntity.setActualEndDate(metricEntity.getActualEndDate());
        metricEntity.setTaskId(metricEntity.getTaskId());

        return metricEntity;
    }

    public static List<MetricEntity> convert(List<MetricDto> metricDtoList){
        return metricDtoList
                .stream()
                .map(MetricEntityConverter::convert)
                .collect(Collectors.toList());
    }
    
}
