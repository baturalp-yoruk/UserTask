package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateProcessRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.converter.CreateProcessRequestConverter;
import com.example.usertask.model.converter.ProcessConverter;
import com.example.usertask.model.converter.TaskConverter;
import com.example.usertask.model.dto.ProcessDto;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.entity.ProcessEntity;
import com.example.usertask.exception.ProcessNotFoundException;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.model.entity.UserEntity;
import com.example.usertask.model.enums.ProcessStatus;
import com.example.usertask.model.enums.TaskStatus;
import com.example.usertask.repositories.ProcessRepository;
import com.example.usertask.repositories.TaskRepository;
import com.example.usertask.repositories.UserRepository;
import com.example.usertask.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.valueOf;

@Service("processServiceImpl")
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<ProcessDto> processList() {
        List<ProcessEntity> processEntities = processRepository.findAll();
        return ProcessConverter.convert(processEntities);
    }

    @Override
    public void createProcess(CreateProcessRequest request) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(()->new UserNotFoundException(request.getUserId()));
        ProcessEntity processEntity = CreateProcessRequestConverter.convert(request);
        processEntity.setUserEntity(userEntity);
        processRepository.save(processEntity);
    }

    @Override
    public ProcessDto getProcessById(int id) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        return ProcessConverter.convert(processEntity);
    }

    @Override
    public ProcessDto updateProcess(int id, ProcessEntity processEntityDetails) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        processEntity.setProcessName(processEntityDetails.getProcessName());
        processEntity.setStartDate(processEntityDetails.getStartDate());
        processEntity.setEndDate(processEntityDetails.getEndDate());
        processEntity.setStatus(processEntityDetails.getStatus());
        //processEntity.setTask(processEntityDetails.getTask());

        ProcessEntity updatedProcessEntity = processRepository.save(processEntity);

        return ProcessConverter.convert(updatedProcessEntity);
    }

    @Override
    public void deleteProcess(int id) throws ProcessNotFoundException{
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        processRepository.delete(processEntity);
    }

    @Override
    public ProcessDto assignProcess(int userid, int processid) throws UserNotFoundException, ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(processid)
                .orElseThrow(()-> new ProcessNotFoundException(processid));

        UserEntity userEntity = userRepository.findById(userid)
                .orElseThrow(()-> new UserNotFoundException(userid));

        processEntity.setUserEntity(userEntity);

        ProcessEntity uodatedProcess = processRepository.save(processEntity);

        return ProcessConverter.convert(uodatedProcess);
    }

    @Override
    public void assignStatus(CreateProcessRequest request , int processid) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(processid)
                .orElseThrow(()-> new ProcessNotFoundException(processid));

        List<TaskEntity> taskEntities = taskRepository.findAllById(request.getTaskId());
        List<TaskDto> taskDtoList = TaskConverter.convert(taskEntities);

        int count1 = 0;
        int count2 = 0;

        for(TaskDto taskDto: taskDtoList){
            if(taskDto.getStatus().equals(TaskStatus.DONE)){
                count2++;
            }
            count1++;
        }

        if(count1 == count2){
            processEntity.setStatus(valueOf(ProcessStatus.DONE));
        }
    }
}
