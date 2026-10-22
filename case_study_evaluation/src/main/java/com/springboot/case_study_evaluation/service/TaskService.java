package com.springboot.case_study_evaluation.service;

import com.springboot.case_study_evaluation.dto.request.TaskDto;
import com.springboot.case_study_evaluation.dto.response.TaskRespDto;
import com.springboot.case_study_evaluation.enums.Status;
import com.springboot.case_study_evaluation.exception.OwnerShipException;
import com.springboot.case_study_evaluation.exception.ResourceNotFoundException;
import com.springboot.case_study_evaluation.mapper.TaskMapper;
import com.springboot.case_study_evaluation.model.Employee;
import com.springboot.case_study_evaluation.model.Task;
import com.springboot.case_study_evaluation.repository.EmployeeRepository;
import com.springboot.case_study_evaluation.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public List<TaskRespDto> getAllTasks(String name, Integer page, Integer size) {

        Pageable pageable= PageRequest.of(page,size);

        Employee employee=employeeRepository.getByUsername(name);

        return taskRepository.getAllTasks(employee.getId(),pageable);
    }

    public TaskRespDto getTaskById(Long taskId) {

        Optional<Task> optional=taskRepository.findById(taskId);

        if(optional.isEmpty()){
            throw new ResourceNotFoundException("Task Not Found");
        }
        Task task=optional.get();
        return TaskMapper.convertEntityToDto(task);
    }

    public void addTask(TaskDto taskDto, Long employeeId) {
        //Get employee obj
        Optional<Employee> optional= employeeRepository.findById(employeeId);

        if(optional.isEmpty())
            throw new ResourceNotFoundException("Employee ID invalid");

        Employee employee=optional.get();

        //Convert Dto to entity
        Task task=TaskMapper.convertDtoToEntity(taskDto);
        task.setEmployee(employee);

        taskRepository.save(task);
    }

    public void updateStatus(Long taskId, Status status, String name) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->new ResourceNotFoundException("Task Not Found"));


        if (!task.getEmployee().getUser().getUsername().equals(name))
            throw new OwnerShipException("This task is not yours");

        task.setStatus(status);
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->new ResourceNotFoundException("Task Not Found"));

        taskRepository.delete(task);
    }
}
