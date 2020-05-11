package com.example.usertask.model.converter;

import com.example.usertask.model.dto.TeamDto;
import com.example.usertask.model.entity.TeamEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TeamConverter {
    public static List<TeamDto> convert(List<TeamEntity> teamEntities){
        return teamEntities
                .stream()
                .map(TeamConverter::convert)
                .collect(Collectors.toList());
    }

    public static TeamDto convert(TeamEntity teamEntity){
        TeamDto teamDto = new TeamDto();
        teamDto.setId(teamEntity.getId());
        teamDto.setName(teamEntity.getName());
        teamDto.setDescription(teamEntity.getDescription());
        teamDto.setUserEntities(teamEntity.getUserEntities());
        return teamDto;
    }
}
