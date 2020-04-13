package com.example.usertask.service;

import com.example.usertask.exception.MetricNotFoundException;
import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.model.dto.MetricDto;
import org.springframework.stereotype.Component;

@Component
public interface MetricService {
    MetricDto assignTask(int taskid, int metricid) throws TaskNotFoundException, MetricNotFoundException;
}
