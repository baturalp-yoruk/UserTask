package com.example.usertask.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TaskDto implements Serializable {
    private int taskId;
    private String taskName;
    private Date startDate;
    private Date endDate;
    private String status;


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
