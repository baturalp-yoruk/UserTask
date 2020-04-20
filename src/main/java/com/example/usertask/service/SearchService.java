package com.example.usertask.service;

import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public interface SearchService {
    List<UserDto> searchUsers(String keyword) throws UserNotFoundException;
    HashMap<TaskDto, List<MetricDto>> getTasksMetrics(int userId) throws UserNotFoundException;
}