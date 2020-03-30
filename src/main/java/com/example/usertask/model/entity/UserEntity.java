package com.example.usertask.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    public UserEntity() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name" , nullable=false, length=200)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role_name" )
    private String role;

    /*@OneToMany(cascade = CascadeType.ALL,
            mappedBy = "userEntity",
            orphanRemoval = true)
    private List<TaskEntity> taskEntities = new ArrayList<TaskEntity>();*/

    public UserEntity(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /*public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public void setTaskEntities(List<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }*/

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                //", taskEntities=" + taskEntities +
                '}';
    }
}

