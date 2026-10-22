package com.springboot.case_study_evaluation.controller;

import com.springboot.case_study_evaluation.dto.request.TaskDto;
import com.springboot.case_study_evaluation.dto.response.TaskRespDto;
import com.springboot.case_study_evaluation.enums.Status;
import com.springboot.case_study_evaluation.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/api/tasks/add/{employeeId}")
    public void addTask(@Valid @RequestBody TaskDto taskDto, @PathVariable Long employeeId){
        taskService.addTask(taskDto,employeeId);
    }


    @GetMapping("/api/tasks/getAllTasks")
    public List<TaskRespDto> getAllTasks(Principal principal,
                                         @RequestParam(name = "page",required = false,defaultValue = "0")Integer page,
                                         @RequestParam(name = "size",required = false,defaultValue = "20")Integer size){
        return taskService.getAllTasks(principal.getName(),page,size);
    }

    @GetMapping("/api/tasks/{taskId}")
    public TaskRespDto getTaskById(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/api/tasks/update/{taskId}/{taskStatus}")
    public void updateStatus(@PathVariable Long taskId,@PathVariable Status taskStatus,Principal principal){
        taskService.updateStatus(taskId,taskStatus,principal.getName());
    }

    @DeleteMapping("/api/tasks/delete/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }
}
