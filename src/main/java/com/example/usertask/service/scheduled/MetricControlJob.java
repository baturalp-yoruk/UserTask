package com.example.usertask.service.scheduled;

import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.converter.MetricConverter;
import com.example.usertask.model.converter.TaskConverter;
import com.example.usertask.model.converter.UserConverter;
import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.dto.UserDto;
import com.example.usertask.model.entity.MetricEntity;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.repositories.MetricRepository;
import com.example.usertask.repositories.TaskRepository;
import com.example.usertask.repositories.UserRepository;
import com.example.usertask.service.MetricService;
import com.example.usertask.service.TaskService;
import com.sun.xml.bind.v2.TODO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class MetricControlJob {

    @Autowired
    private MetricRepository metricRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    private final Supplier<Predicate<MetricDto>> findOverDueMetrics = () -> metricDto -> metricDto.getActualEndDate()
            .compareTo(metricDto.getOriginalEndDate()) > 0;

    @Scheduled(fixedRate = 5000)
    public void control() throws TaskNotFoundException, UserNotFoundException {
        List<TaskDto> taskList = new ArrayList<>();
        List<UserDto> userList = new ArrayList<>();
        List<MetricDto> allMetrics = MetricConverter.convert(metricRepository.findAll());
        List<MetricDto> overDueMetricList = getOverDueMetrics(allMetrics);

        for(MetricDto overDueMetric: overDueMetricList){
            TaskDto overDueTask = getOverDueTask(overDueMetric);
            taskList.add(overDueTask);
            userList.add(getOverDueUser(overDueTask));
        }

        printOverDueMetrics(overDueMetricList);
        printOverDueTasks(taskList);
        printOverDueUsers(userList);

    }

    private List<MetricDto> getOverDueMetrics(List<MetricDto> allMetrics) {
        return allMetrics.stream().filter(findOverDueMetrics.get()).collect(Collectors.toList());
    }

    private TaskDto getOverDueTask(MetricDto overDueMetric) throws TaskNotFoundException {
        return TaskConverter.convert(taskRepository.findById(overDueMetric.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(overDueMetric.getTaskId())));
    }

    private UserDto getOverDueUser(TaskDto overDueTask) throws UserNotFoundException {
        return UserConverter.convert(userRepository.findById(overDueTask.getUserId()).
                orElseThrow(() -> new UserNotFoundException(overDueTask.getUserId())));
    }

    private void printOverDueMetrics(List<MetricDto> overDueMetricList){
        for(MetricDto metricDto: overDueMetricList){
            System.out.println(metricDto);
        }
    }

    private void printOverDueTasks(List<TaskDto> overDueTaskList){
        for(TaskDto taskDto: overDueTaskList){
            System.out.println(taskDto);
        }
    }

    private void printOverDueUsers(List<UserDto> overDueUserList){
        for(UserDto userDto: overDueUserList){
            System.out.println(userDto);
        }
    }
}
