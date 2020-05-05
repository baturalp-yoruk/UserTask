package com.example.usertask.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class UserDto implements Serializable {
    private String userName;
    private String role;
    private int userId;
    private List<TaskDto> taskDtoList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<TaskDto> getTaskDtoList() {
        return taskDtoList;
    }

    public void setTaskDtoList(List<TaskDto> taskDtoList) {
        this.taskDtoList = taskDtoList;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userName, userDto.userName) &&
                Objects.equals(role, userDto.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, role);
    }
}
