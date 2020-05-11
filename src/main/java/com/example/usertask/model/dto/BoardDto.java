package com.example.usertask.model.dto;

import com.example.usertask.model.entity.ColumnEntity;
import com.example.usertask.model.entity.TeamEntity;

import java.util.List;

public class BoardDto {
    private Long id;
    private String name;
    private String description;
    private TeamEntity teamEntity;
    private List<ColumnEntity> columnEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    public List<ColumnEntity> getColumnEntities() {
        return columnEntities;
    }

    public void setColumnEntities(List<ColumnEntity> columnEntities) {
        this.columnEntities = columnEntities;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", teamEntity=" + teamEntity +
                ", columnEntities=" + columnEntities +
                '}';
    }
}
