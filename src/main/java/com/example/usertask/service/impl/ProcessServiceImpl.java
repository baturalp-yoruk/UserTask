package com.example.usertask.service.impl;

import com.example.usertask.controller.request.CreateProcessRequest;
import com.example.usertask.controller.request.UpdateProcessRequest;
import com.example.usertask.exception.ProcessNotFoundException;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.converter.CreateProcessRequestConverter;
import com.example.usertask.model.converter.ProcessConverter;
import com.example.usertask.model.converter.TaskConverter;
import com.example.usertask.model.converter.UserEntityConverter;
import com.example.usertask.model.dto.ProcessDto;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.dto.UserDto;
import com.example.usertask.model.entity.ProcessEntity;
import com.example.usertask.model.entity.TaskEntity;
import com.example.usertask.model.entity.UserEntity;
import com.example.usertask.model.enums.ProcessStatus;
import com.example.usertask.model.enums.TaskStatus;
import com.example.usertask.repositories.ProcessRepository;
import com.example.usertask.repositories.TaskRepository;
import com.example.usertask.repositories.UserRepository;
import com.example.usertask.service.ProcessService;
import com.example.usertask.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@Service
public class ProcessServiceImpl implements ProcessService {

    private final ProcessRepository processRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserService userService;

    public ProcessServiceImpl(ProcessRepository processRepository, UserRepository userRepository,
                              TaskRepository taskRepository, UserService userService) {
        this.processRepository = processRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public List<ProcessDto> processList() {
        return ProcessConverter.convert(getNotDeletedProcessEntities());
    }

    @Override
    public void createProcess(CreateProcessRequest request) {
        ProcessEntity processEntity = CreateProcessRequestConverter.convert(request);
        processRepository.save(processEntity);
    }

    @Override
    public ProcessDto getProcessById(int id) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        return processEntity.isDeleted() ? null : ProcessConverter.convert(processEntity);
    }

    @Override
    public ProcessDto updateProcess(int id, UpdateProcessRequest updateProcessRequest) throws ProcessNotFoundException, UserNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        //TODO: Check whether it works
        UserDto userDto = userService.getUserById(updateProcessRequest.getUserId());

        UserEntity userEntity = UserEntityConverter.convert(userDto);

        prepareProcessEntity(updateProcessRequest, processEntity, userEntity);

        return ProcessConverter.convert(processRepository.save(processEntity));
    }

    @Override
    public ProcessDto deleteProcess(int id) throws ProcessNotFoundException{
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        List<TaskEntity> taskList = processEntity.getTaskEntities();
        processEntity.setDeleted(true);
        taskList.stream().forEach(task -> task.setDeleted(true));

        return ProcessConverter.convert(processRepository.save(processEntity));

    }

    @Override
    public ProcessDto assignProcess(int userId, int processId) throws UserNotFoundException, ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(processId)
                .orElseThrow(()-> new ProcessNotFoundException(processId));

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));

        processEntity.setUserEntity(userEntity);

        return ProcessConverter.convert(processRepository.save(processEntity));
    }

    @Override
    public void assignStatus(UpdateProcessRequest request , int processId) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(processId)
                .orElseThrow(()-> new ProcessNotFoundException(processId));

        List<TaskEntity> taskEntities = processEntity.getTaskEntities();
        List<TaskDto> taskDtoList = TaskConverter.convert(taskEntities);

        setStatus(processEntity, taskDtoList);

    }

    private void setStatus(ProcessEntity processEntity, List<TaskDto> taskDtoList) {
        int taskCount = 0;
        int completedTaskCount = 0;

        for(TaskDto taskDto: taskDtoList){
            if(taskDto.getStatus().equals(TaskStatus.DONE))
                completedTaskCount++;
            taskCount++;
        }

        if(taskCount == completedTaskCount)
            processEntity.setStatus(valueOf(ProcessStatus.DONE));
    }

    private void prepareProcessEntity(UpdateProcessRequest request, ProcessEntity processEntity, UserEntity userEntity) {
        if(request.getProcessName() != null)
            processEntity.setProcessName(request.getProcessName());

        if(request.getUserId() != 0)
            processEntity.setUserEntity(userEntity);

        if(request.getProcessStatus() != null)
            processEntity.setStatus(String.valueOf(request.getProcessStatus()));
    }

    private List<ProcessEntity> getNotDeletedProcessEntities() {
        return processRepository.
                findAll().stream().filter(processEntity -> !processEntity.isDeleted()) .collect(Collectors.toList());
    }

}
