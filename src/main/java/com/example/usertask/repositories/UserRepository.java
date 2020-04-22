package com.example.usertask.repositories;

import com.example.usertask.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u.userName FROM UserEntity u WHERE u.userName LIKE :username%")
    List<UserEntity> findAllByUserName(@Param("username") String username);
}
