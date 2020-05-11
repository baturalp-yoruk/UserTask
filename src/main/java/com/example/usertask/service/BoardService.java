package com.example.usertask.service;

import com.example.usertask.controller.request.CreateBoardRequest;
import com.example.usertask.model.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoardService {
    List<BoardDto> boardList();
    void createBoard(CreateBoardRequest request);
}
