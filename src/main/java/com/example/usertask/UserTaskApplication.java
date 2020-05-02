package com.example.usertask;

import com.example.usertask.model.entity.MetricEntity;
import com.example.usertask.model.entity.ProcessEntity;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.model.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class UserTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTaskApplication.class, args);
	}

}
