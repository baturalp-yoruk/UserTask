package com.example.usertask.controller;

import com.example.usertask.controller.request.CreateProcessRequest;
import com.example.usertask.controller.request.UpdateProcessRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.dto.ProcessDto;
import com.example.usertask.model.entity.ProcessEntity;
import com.example.usertask.exception.ProcessNotFoundException;
import com.example.usertask.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @GetMapping("/processes")
    public List<ProcessDto> processList(){
        return processService.processList();
    }

    @PostMapping("/processes")
    public void createProcess(@RequestBody CreateProcessRequest request) throws UserNotFoundException {
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

    @PutMapping("/processes/{userid}/{processid}")
    public ProcessDto assignProcess(@PathVariable(value = "userid") int userid, @PathVariable(value = "processid")
            int processid) throws ProcessNotFoundException, UserNotFoundException {
        return processService.assignProcess(userid,processid);
    }

    @PutMapping("/processes/status/{processid}")
    public void assignStatus(CreateProcessRequest request , @PathVariable(value = "processid") int processid) throws ProcessNotFoundException {
        processService.assignStatus(request,processid);
    }

}
