package com.example.usertask.model.entity;


import com.example.usertask.model.enums.MetricType;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "metric")
public class MetricEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "metric_type")
    private MetricType metricType;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "original_end_date")
    private LocalDateTime originalEndDate;

    @Column(name = "actual_end_date")
    private LocalDateTime actualEndDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable = true, insertable = true)
    private TaskEntity taskEntity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable = true, insertable = true)
    private UserEntity userEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getOriginalEndDate() {
        return originalEndDate;
    }

    public void setOriginalEndDate(LocalDateTime originalEndDate) {
        this.originalEndDate = originalEndDate;
    }

    public LocalDateTime getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDateTime actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "MetricEntity{" +
                "id=" + id +
                ", metricType=" + metricType +
                ", startDate=" + startDate +
                ", originalEndDate=" + originalEndDate +
                ", actualEndDate=" + actualEndDate +
                '}';
    }
}
