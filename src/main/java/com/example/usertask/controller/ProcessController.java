package com.example.usertask.controller;

import com.example.usertask.controller.request.CreateProcessRequest;
import com.example.usertask.controller.request.UpdateProcessRequest;
import com.example.usertask.exception.ProcessNotFoundException;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.dto.ProcessDto;
import com.example.usertask.service.ProcessService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProcessController {
    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping("/processes")
    public List<ProcessDto> processList(){
        return processService.processList();
    }

    @PostMapping("/processes")
    public void createProcess(@Valid @RequestBody CreateProcessRequest request) throws UserNotFoundException {
        processService.createProcess(request);
    }

    @GetMapping("/processes/{id}")
    public ProcessDto getProcessById(@PathVariable(value = "id") int id) throws ProcessNotFoundException {
        return processService.getProcessById(id);
    }

    @PutMapping("/processes/{id}")
    public ProcessDto updateProcess(@PathVariable(value = "id") int id,
                                         @Valid @RequestBody UpdateProcessRequest updateProcessRequest) throws ProcessNotFoundException, UserNotFoundException {
        return processService.updateProcess(id, updateProcessRequest);
    }


    @DeleteMapping("/processes/{id}")
    public void deleteProcess(@PathVariable(value = "id") int id) throws ProcessNotFoundException {
        processService.deleteProcess(id);
    }

    @PutMapping("/processes/{processId}/{userId}")
    public ProcessDto assignProcess(@PathVariable(value = "processId") int processId,
                                    @PathVariable(value = "userId") int userId) throws ProcessNotFoundException, UserNotFoundException {
        return processService.assignProcess(userId,processId);
    }

    @PutMapping("/processes/status/{processId}")
    public void assignStatus(@Valid @RequestBody  UpdateProcessRequest request , @PathVariable(value = "processId") int processId) throws ProcessNotFoundException {
        processService.assignStatus(request,processId);
    }

}
