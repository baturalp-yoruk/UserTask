package com.example.usertask.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "process")
public class ProcessEntity {

    public ProcessEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "process_name")
    private String processName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status")
    private String status;

    @Column(name = "deleted")
    private boolean deleted;

    /*@OneToMany(cascade = CascadeType.ALL,
            mappedBy = "processEntity",
            orphanRemoval = true)
    private List<TaskEntity> taskEntities = new ArrayList<>();*/

    @OneToOne
    @JoinColumn(updatable = false, insertable = false)
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProcessEntity(String processName, Date startDate, Date endDate) {
        this.processName = processName;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setProcessName(String processName){
        this.processName = processName;
    }

    public String getProcessName(){
        return processName;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }


    public Date getStartDate(){
        return startDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*public List<TaskEntity> getTask(){
        return taskEntities;
    }

    public void setTask(List<TaskEntity> taskEntities){
        this.taskEntities = taskEntities;
    }*/

}
