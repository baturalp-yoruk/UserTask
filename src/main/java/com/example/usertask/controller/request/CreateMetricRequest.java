package com.example.usertask.controller.request;

import com.example.usertask.model.enums.MetricType;

import java.time.LocalDateTime;

public class CreateMetricRequest {

    private MetricType metricType;
    private LocalDateTime startDate;
    private LocalDateTime originalEndDate;
    private LocalDateTime actualEndDate;

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getOriginalEndDate() {
        return originalEndDate;
    }

    public void setOriginalEndDate(LocalDateTime originalEndDate) {
        this.originalEndDate = originalEndDate;
    }

    public LocalDateTime getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDateTime actualEndDate) {
        this.actualEndDate = actualEndDate;
    }
}
