package com.example.usertask.service.scheduled;

import com.example.usertask.exception.TaskNotFoundException;
import com.example.usertask.model.entity.MetricEntity;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.repositories.MetricRepository;
import com.example.usertask.repositories.TaskRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Configuration
@EnableScheduling
public class MetricControlJob {

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(fixedRate = 5000)
    public void control() throws TaskNotFoundException {

        for (MetricEntity metricEntity : metricRepository.findAll()){

            TaskEntity taskEntity = taskRepository.findById(metricEntity.getTaskId())
                    .orElseThrow(() -> new TaskNotFoundException(metricEntity.getTaskId()));

            if (metricEntity.getActualEndDate().compareTo(metricEntity.getOriginalEndDate()) > 0) {
                System.out.println("This Metric deadline exceeded by "
                        + ChronoUnit.DAYS.between(metricEntity.getActualEndDate(),
                        metricEntity.getOriginalEndDate()) + " days, at the " + java.time.LocalDate.now()
                        + "by the user: " + taskEntity.getUserEntity().getUserName());

            }
        }
    }

}
