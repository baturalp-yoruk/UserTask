package com.example.usertask.model.converter;

import com.example.usertask.controller.request.CreateTeamRequest;
import com.example.usertask.model.entity.TeamEntity;

public class CreateTeamRequestConverter {
    public static TeamEntity convert(CreateTeamRequest request){
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(request.getName());
        teamEntity.setDescription(request.getDescription());
        return teamEntity;
    }
}
