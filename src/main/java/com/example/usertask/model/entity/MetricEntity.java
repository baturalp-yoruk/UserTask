package com.example.usertask.model.entity;


import com.example.usertask.model.enums.MetricType;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;

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
    private Date startDate;

    @Column(name = "original_end_date")
    private Date originalEndDate;

    @Column(name = "actual_end_date")
    private Date actualEndDate;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    private TaskEntity taskEntity;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getOriginalEndDate() {
        return originalEndDate;
    }

    public void setOriginalEndDate(Date originalEndDate) {
        this.originalEndDate = originalEndDate;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    @Override
    public String toString() {
        return "MetricEntity{" +
                "id=" + id +
                ", metricType=" + metricType +
                ", startDate=" + startDate +
                ", originalEndDate=" + originalEndDate +
                ", actualEndDate=" + actualEndDate +
                ", taskEntity=" + taskEntity +
                '}';
    }
}
