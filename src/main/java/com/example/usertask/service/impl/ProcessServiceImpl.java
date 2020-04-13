package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateProcessRequest;
import com.example.usertask.controller.request.UpdateProcessRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.converter.*;
import com.example.usertask.model.dto.ProcessDto;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.dto.UserDto;
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
import com.example.usertask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<ProcessDto> processList() {

        List<ProcessEntity> processEntities = processRepository.
                findAll().stream().filter(processEntity -> !processEntity.isDeleted()) .collect(Collectors.toList());

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

        if(processEntity.isDeleted()){
            return null;
        }

        return ProcessConverter.convert(processEntity);
    }

    @Override
    public ProcessDto updateProcess(int id, UpdateProcessRequest updateProcessRequest) throws ProcessNotFoundException, UserNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        UserDto userDto = userService.getUserById(updateProcessRequest.getUserId());

        UserEntity userEntity = UserEntityConverter.convert(userDto);

        prepareProcessEntity(updateProcessRequest, processEntity, userEntity);

        ProcessEntity updatedProcessEntity = processRepository.save(processEntity);

        return ProcessConverter.convert(updatedProcessEntity);
    }

    @Override
    public ProcessDto deleteProcess(int id) throws ProcessNotFoundException{
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        List<TaskDto> taskDtos =  TaskConverter.convert(processEntity.getTaskEntities());

        for(TaskDto taskDto: taskDtos){
            taskDto.setDeleted(true);
        }

        processEntity.setDeleted(true);

        ProcessEntity updatedProcessEntity = processRepository.save(processEntity);

        return ProcessConverter.convert(updatedProcessEntity);

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

    private void prepareProcessEntity(UpdateProcessRequest request, ProcessEntity processEntity, UserEntity userEntity) {
        if(request.getProcessName() != null){
            processEntity.setProcessName(request.getProcessName());
        }
        if(request.getUserId() != 0){
            processEntity.setUserEntity(userEntity);
        }
        if(request.getProcessStatus() != null){
            processEntity.setStatus(String.valueOf(request.getProcessStatus()));
        }
    }

}
