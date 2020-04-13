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



@SpringBootApplication
public class UserTaskApplication {

	public static void main(String[] args) {

		SessionFactory sessionFactory =(SessionFactory) new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserEntity.class)
				.addAnnotatedClass(TaskEntity.class)
				.addAnnotatedClass(ProcessEntity.class)
				.addAnnotatedClass(MetricEntity.class).buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		SpringApplication.run(UserTaskApplication.class, args);
	}

}
