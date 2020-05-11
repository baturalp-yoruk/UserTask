package com.example.usertask.service;

import com.example.usertask.controller.request.CreateTeamRequest;
import com.example.usertask.model.dto.TeamDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeamService {
    List<TeamDto> teamList();
    void createTeam(CreateTeamRequest request);
}
