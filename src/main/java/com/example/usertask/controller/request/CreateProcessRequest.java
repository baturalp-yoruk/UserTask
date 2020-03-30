package com.example.usertask.controller.request;

import java.util.Collections;
import java.util.Date;

public class CreateProcessRequest {
    private String processName;
    private Date startDate;
    private Date endDate;
    private String status;
    private int userId;
    private int taskId;

    public Iterable<Integer> getTaskId() {
        return Collections.singleton(taskId);
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
