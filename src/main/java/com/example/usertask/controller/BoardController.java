package com.example.usertask.controller;

import com.example.usertask.controller.request.CreateBoardRequest;
import com.example.usertask.model.dto.BoardDto;
import com.example.usertask.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public List<BoardDto> boardList(){
        return boardService.boardList();
    }

    @PostMapping("/board")
    public void createBoard(@Valid @RequestBody CreateBoardRequest request) {
        boardService.createBoard(request);
    }
}
