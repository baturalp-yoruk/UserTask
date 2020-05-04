package com.example.usertask.service.impl;

import com.example.usertask.exception.MetricNotFoundException;
import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.model.converter.MetricConverter;
import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.entity.MetricEntity;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.repositories.MetricRepository;
import com.example.usertask.repositories.TaskRepository;
import com.example.usertask.service.MetricService;
import org.springframework.stereotype.Service;

@Service
public class MetricServiceImpl implements MetricService {

    private final MetricRepository metricRepository;
    private final TaskRepository taskRepository;

    public MetricServiceImpl(MetricRepository metricRepository, TaskRepository taskRepository) {
        this.metricRepository = metricRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public MetricDto assignTask(int taskId, int metricId) throws TaskNotFoundException, MetricNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        MetricEntity metricEntity = metricRepository.findById(metricId).orElseThrow(() -> new MetricNotFoundException(metricId));

        metricEntity.setTaskEntity(taskEntity);

        return MetricConverter.convert(metricRepository.save(metricEntity));

    }
}
