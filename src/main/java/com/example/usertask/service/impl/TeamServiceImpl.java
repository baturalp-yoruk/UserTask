package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateTeamRequest;
import com.example.usertask.model.converter.CreateTeamRequestConverter;
import com.example.usertask.model.converter.TeamConverter;
import com.example.usertask.model.dto.TeamDto;
import com.example.usertask.repositories.TeamRepository;
import com.example.usertask.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<TeamDto> teamList() {
        return TeamConverter.convert(teamRepository.findAll());
    }

    @Override
    public void createTeam(CreateTeamRequest request) {
        teamRepository.save(CreateTeamRequestConverter.convert(request));
    }
}
