package com.example.usertask.service;

import com.example.usertask.controller.request.CreateProcessRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.dto.ProcessDto;
import com.example.usertask.model.entity.ProcessEntity;
import com.example.usertask.exception.ProcessNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProcessService {
    List<ProcessDto> processList();
    void createProcess(CreateProcessRequest request) throws UserNotFoundException;
    ProcessDto getProcessById(int id) throws ProcessNotFoundException;
    ProcessDto updateProcess(int id, ProcessEntity processEntityDetails) throws ProcessNotFoundException;
    void deleteProcess(int id) throws ProcessNotFoundException;
    ProcessDto assignProcess(int userid, int processid) throws ProcessNotFoundException,UserNotFoundException;
    void assignStatus(CreateProcessRequest request, int processid) throws ProcessNotFoundException;
}
