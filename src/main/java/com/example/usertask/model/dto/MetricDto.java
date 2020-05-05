package com.example.usertask.model.dto;

import com.example.usertask.model.enums.MetricType;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MetricDto implements Serializable {

    private int id;
    private MetricType metricType;
    private LocalDateTime startDate;
    private LocalDateTime originalEndDate;
    private LocalDateTime actualEndDate;
    private int taskId;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MetricDto{" +
                "id=" + id +
                ", metricType=" + metricType +
                ", startDate=" + startDate +
                ", originalEndDate=" + originalEndDate +
                ", actualEndDate=" + actualEndDate +
                ", taskId=" + taskId +
                '}';
    }
}
