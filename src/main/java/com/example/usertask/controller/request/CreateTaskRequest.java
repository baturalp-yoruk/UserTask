package com.example.usertask.controller.request;

import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.dto.UserDto;
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
    private UserDto userDto;
    private List<MetricDto> metricDtos;

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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<MetricDto> getMetricDtos() {
        return metricDtos;
    }

    public void setMetricDtos(List<MetricDto> metricDtos) {
        this.metricDtos = metricDtos;
    }
}
