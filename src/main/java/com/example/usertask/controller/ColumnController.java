package com.example.usertask.controller;

import com.example.usertask.controller.request.CreateColumnRequest;
import com.example.usertask.model.dto.ColumnDto;
import com.example.usertask.service.ColumnService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ColumnController {
    private final ColumnService columnService;

    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @GetMapping("/column")
    public List<ColumnDto> columnList(){
        return columnService.columnList();
    }

    @PostMapping("/column")
    public void createColumn(@Valid @RequestBody CreateColumnRequest request) {
        columnService.createColumn(request);
    }
}
