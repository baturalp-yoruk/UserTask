package com.example.usertask.controller.request;

import com.example.usertask.model.dto.MetricDto;
import java.util.List;

public class CreateMetricRequest {

    private List<MetricDto> metrics;
    private int taskId;

    public List<MetricDto> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricDto> metrics) {
        this.metrics = metrics;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
