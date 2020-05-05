package com.example.usertask.controller.request;

import java.util.Date;

public class CreateProcessRequest {
    private String processName;
    private Date startDate;
    private Date endDate;
    private String status;
    private Iterable<Integer> taskId;
    private int userId;

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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

    public Iterable<Integer> getTaskId() {
        return taskId;
    }

    public void setTaskId(Iterable<Integer> taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
