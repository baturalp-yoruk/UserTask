package com.example.usertask.controller;

import com.example.usertask.controller.request.CreateMetricRequest;
import com.example.usertask.exception.MetricNotFoundException;
import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.service.MetricService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MetricController {

    private final MetricService metricService;
    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping("/metrics")
    public List<MetricDto> metricList(){
        return metricService.metricList();
    }

    @PostMapping("/metrics")
    public void createMetric(@RequestBody CreateMetricRequest request) {
        metricService.createMetric(request);
    }

    @PutMapping("/metrics/{metricId}/{taskId}")
    public MetricDto assignTask(@PathVariable(value = "metricId") int metricId, @PathVariable(name = "taskId") int taskId) throws TaskNotFoundException, MetricNotFoundException{
        return metricService.assignTask(taskId, metricId);
    }


}
