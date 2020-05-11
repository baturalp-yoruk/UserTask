package com.example.usertask.model.converter;

import com.example.usertask.controller.request.CreateBoardRequest;
import com.example.usertask.model.entity.BoardEntity;

public class CreateBoardRequestConverter {
    public static BoardEntity convert(CreateBoardRequest request){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(request.getName());
        boardEntity.setDescription(request.getDescription());
        return boardEntity;
    }
}
