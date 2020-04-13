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

    @Autowired
    private MetricService metricService;

    @PutMapping("/metrics/{taskid}/{metricid}")
    public MetricDto assignTask(@PathVariable(value = "taskid") int taskid, @PathVariable(name = "metricid") int metricid) throws TaskNotFoundException, MetricNotFoundException{
        return metricService.assignTask(taskid, metricid);
    }


}
