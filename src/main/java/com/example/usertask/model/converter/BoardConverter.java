package com.example.usertask.model.converter;

import com.example.usertask.model.dto.BoardDto;
import com.example.usertask.model.entity.BoardEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BoardConverter {

    public static List<BoardDto> convert(List<BoardEntity> boardEntities){
        return boardEntities
                .stream()
                .map(BoardConverter::convert)
                .collect(Collectors.toList());
    }

    public static BoardDto convert(BoardEntity boardEntity){
        BoardDto boardDto = new BoardDto();
        boardDto.setId(boardEntity.getId());
        boardDto.setName(boardEntity.getName());
        boardDto.setDescription(boardEntity.getDescription());
        boardDto.setTeamEntity(boardEntity.getTeamEntity());
        boardDto.setColumnEntities(boardEntity.getColumnEntities());
        return boardDto;
    }
}
