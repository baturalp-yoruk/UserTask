package com.example.usertask.controller;

import com.example.usertask.exception.MetricNotFoundException;
import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @PutMapping("/metrics/{taskId}/{metricId}")
    public MetricDto assignTask(@PathVariable(value = "taskId") int taskId, @PathVariable(name = "metricId") int metricId) throws TaskNotFoundException, MetricNotFoundException{
        return metricService.assignTask(taskId, metricId);
    }


}
