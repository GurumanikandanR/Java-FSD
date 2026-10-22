package com.springboot.case_study_evaluation.mapper;

import com.springboot.case_study_evaluation.dto.request.TaskDto;
import com.springboot.case_study_evaluation.dto.response.TaskRespDto;
import com.springboot.case_study_evaluation.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public static Task convertDtoToEntity(TaskDto taskDto){
        Task task=new Task();
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setDueDate(taskDto.dueDate());
        task.setPriority(taskDto.priority());
        task.setStatus(taskDto.status());
        return task;
    }



    public static TaskRespDto convertEntityToDto(Task task){
        return new TaskRespDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
