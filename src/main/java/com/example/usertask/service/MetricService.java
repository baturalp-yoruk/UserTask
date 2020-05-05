package com.example.usertask.service;

import com.example.usertask.controller.request.CreateMetricRequest;
import com.example.usertask.exception.MetricNotFoundException;
import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.model.dto.MetricDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MetricService {

    List<MetricDto> metricList();

    void createMetric(CreateMetricRequest request);
    MetricDto assignTask(int taskId, int metricId) throws TaskNotFoundException, MetricNotFoundException;
}
