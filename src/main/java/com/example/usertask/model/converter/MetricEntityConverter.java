package com.example.usertask.model.converter;

import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.entity.MetricEntity;
import com.example.usertask.repositories.MetricRepository;
import com.example.usertask.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MetricEntityConverter {

    private final MetricRepository metricRepository;

    public MetricEntityConverter(MetricRepository metricRepository){
        this.metricRepository = metricRepository;
    }

    public static MetricEntity convert(MetricDto metricDto){
        MetricEntity metricEntity = new MetricEntity();

        metricEntity.setId(metricDto.getId());
        metricEntity.setStartDate(metricDto.getStartDate());
        metricEntity.setOriginalEndDate(metricDto.getOriginalEndDate());
        metricEntity.setActualEndDate(metricDto.getActualEndDate());

        return metricEntity;
    }

    public static List<MetricEntity> convert(List<MetricDto> metricDtoList){
        return metricDtoList
                .stream()
                .map(MetricEntityConverter::convert)
                .collect(Collectors.toList());
    }
    
}
