package com.example.usertask.service.scheduled;

import com.example.usertask.model.entity.MetricEntity;
import com.example.usertask.repositories.MetricRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.temporal.ChronoUnit;

@Configuration
@EnableScheduling
public class MetricControlJob {

    @Autowired
    private MetricRepository metricRepository;


    @Scheduled(fixedDelay = 1000)
    public void control() {

        for (MetricEntity metricEntity : metricRepository.findAll()){

            if (metricEntity.getActualEndDate().compareTo(metricEntity.getOriginalEndDate()) > 0) {
                System.out.println("This Metric deadline exceeded by "
                        + ChronoUnit.DAYS.between(metricEntity.getActualEndDate().toInstant(),
                        metricEntity.getOriginalEndDate().toInstant()) + " days, at the " + java.time.LocalDate.now()
                        + "by the user: " + metricEntity.getTaskEntity().getUserEntity().getUserName());

            }
        }
    }

}
