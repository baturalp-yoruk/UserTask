package com.example.usertask.controller;

import com.example.usertask.controller.request.CreateUserRequest;
import com.example.usertask.controller.request.UpdateUserRequest;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.model.dto.MetricDto;
import com.example.usertask.model.dto.TaskDto;
import com.example.usertask.model.dto.UserDto;
import com.example.usertask.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> userList(){
        return userService.userList();
    }

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable(value = "id") int id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable(value = "id") int id,
                                            @Validated @RequestBody UpdateUserRequest updateUserRequest) throws UserNotFoundException {
        return userService.updateUser(id,updateUserRequest);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(value = "id") int id) throws UserNotFoundException {
        userService.deleteUser(id);
    }

    @GetMapping("/users/search/{keyword}")
    public List<UserDto> getUserNameByName(@PathVariable(value = "keyword") String keyword){
        return userService.getUserNameByName(keyword);
    }

    @GetMapping("/users/tasksMetrics/{id}")
    public Map<TaskDto, List<MetricDto>> getTasksMetrics(@PathVariable(value = "id") int id) throws UserNotFoundException {
        return userService.getTasksMetrics(id);
    }


}
