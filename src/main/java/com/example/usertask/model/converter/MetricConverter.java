package com.example.usertask.model.converter;

import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.entity.MetricEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MetricConverter {

    public static MetricDto convert(MetricEntity metricEntity){
        MetricDto metricDto = new MetricDto();

        metricDto.setMetricId(metricEntity.getId());
        metricDto.setStartDate(metricEntity.getStartDate());
        metricDto.setOriginalEndDate(metricEntity.getOriginalEndDate());
        metricDto.setActualEndDate(metricEntity.getActualEndDate());
        metricDto.setTaskEntity(metricEntity.getTaskEntity());

        return metricDto;
    }

    public static List<MetricDto> convert(List<MetricEntity> metricEntityList){
        return metricEntityList
                .stream()
                .map(MetricConverter::convert)
                .collect(Collectors.toList());
    }

}
