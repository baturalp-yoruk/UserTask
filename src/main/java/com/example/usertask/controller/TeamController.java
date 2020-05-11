package com.example.usertask.controller;

import com.example.usertask.controller.request.CreateTeamRequest;
import com.example.usertask.model.dto.TeamDto;
import com.example.usertask.service.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/team")
    public List<TeamDto> teamList(){
        return teamService.teamList();
    }

    @PostMapping("/team")
    public void createTeam(@Valid @RequestBody CreateTeamRequest request) {
        teamService.createTeam(request);
    }
}
