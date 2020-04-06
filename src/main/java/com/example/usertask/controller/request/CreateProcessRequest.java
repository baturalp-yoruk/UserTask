package com.example.usertask.controller.request;

import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.model.entity.UserEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CreateProcessRequest {
    private String processName;
    private Date startDate;
    private Date endDate;
    private String status;
    private int userId;
    private int taskId;
    private List<TaskEntity> taskEntities;
    private boolean deleted;
    private UserEntity userEntity;



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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public void setTaskEntities(List<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
