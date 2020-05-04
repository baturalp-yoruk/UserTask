package com.example.usertask.model.dto;

import com.example.usertask.model.entity.MetricEntity;
import com.example.usertask.model.entity.UserEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TaskDto implements Serializable {
    private int taskId;
    private String taskName;
    private Date startDate;
    private Date endDate;
    private String status;
    private String description;
    private boolean deleted;
    private int userId;
    private List<MetricDto> metricDtoList;


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<MetricDto> getMetricDtoList() {
        return metricDtoList;
    }

    public void setMetricDtoList(List<MetricDto> metricDtoList) {
        this.metricDtoList = metricDtoList;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDto taskDto = (TaskDto) o;
        return taskId == taskDto.taskId &&
                Objects.equals(taskName, taskDto.taskName) &&
                Objects.equals(startDate, taskDto.startDate) &&
                Objects.equals(endDate, taskDto.endDate) &&
                Objects.equals(status, taskDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskName, startDate, endDate, status);
    }
}
