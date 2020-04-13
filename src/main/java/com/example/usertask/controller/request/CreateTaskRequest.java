package com.example.usertask.controller.request;

import com.example.usertask.model.entity.MetricEntity;
import com.example.usertask.model.entity.UserEntity;

import java.util.Date;
import java.util.List;

public class CreateTaskRequest {
    private String taskName;
    private Date startDate;
    private Date endDate;
    private String status;
    private String description;
    private boolean deleted;
    private UserEntity userEntity;
    private List<MetricEntity> metricEntities;

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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<MetricEntity> getMetricEntities() {
        return metricEntities;
    }

    public void setMetricEntities(List<MetricEntity> metricEntities) {
        this.metricEntities = metricEntities;
    }
}
