package com.example.usertask.model.converter;

import com.example.usertask.controller.request.CreateMetricRequest;
import com.example.usertask.model.entity.MetricEntity;

public class CreateMetricRequestConverter {

    public static MetricEntity convert(CreateMetricRequest request){
        MetricEntity metricEntity = new MetricEntity();
        metricEntity.setMetricType(request.getMetricType());
        metricEntity.setStartDate(request.getStartDate());
        metricEntity.setActualEndDate(request.getActualEndDate());
        metricEntity.setOriginalEndDate(request.getOriginalEndDate());

        return metricEntity;
    }
}
