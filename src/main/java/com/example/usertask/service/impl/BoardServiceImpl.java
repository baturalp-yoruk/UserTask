package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateBoardRequest;
import com.example.usertask.model.converter.BoardConverter;
import com.example.usertask.model.converter.CreateBoardRequestConverter;
import com.example.usertask.model.dto.BoardDto;
import com.example.usertask.repositories.BoardRepository;
import com.example.usertask.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<BoardDto> boardList() {
        return BoardConverter.convert(boardRepository.findAll());
    }

    @Override
    public void createBoard(CreateBoardRequest request) {
        boardRepository.save(CreateBoardRequestConverter.convert(request));
    }
}
