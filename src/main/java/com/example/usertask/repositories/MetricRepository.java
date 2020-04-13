package com.example.usertask.repositories;

import com.example.usertask.model.entity.MetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<MetricEntity, Integer> {

}
