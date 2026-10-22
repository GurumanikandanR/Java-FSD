package com.springboot.case_study_evaluation.service;

import com.springboot.case_study_evaluation.repository.EmployeeRepository;
import com.springboot.case_study_evaluation.repository.TaskRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.springboot.case_study_evaluation.dto.response.TaskRespDto;
import com.springboot.case_study_evaluation.model.Task;
import org.junit.jupiter.api.Test;
import com.springboot.case_study_evaluation.dto.request.TaskDto;
import com.springboot.case_study_evaluation.enums.Priority;
import com.springboot.case_study_evaluation.enums.Role;
import com.springboot.case_study_evaluation.enums.Status;
import com.springboot.case_study_evaluation.model.Employee;
import com.springboot.case_study_evaluation.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void getTaskByIdTest() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Complete Task 1");

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        TaskRespDto response = taskService.getTaskById(1L);

        assertEquals(1L, response.id());
        assertEquals("Complete Task 1", response.title());

        verify(taskRepository).findById(1L);
    }

    @Test
    void getAllTasksTest() {
        User user = new User("frodo", "frodo@123", Role.EMPLOYEE);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("frodo baggins");
        employee.setUser(user);

        TaskRespDto dto = new TaskRespDto(
                1L,
                "Complete Task 1",
                "Implement Task 1",
                LocalDate.of(2026, 10, 30),
                Priority.HIGH,
                Status.PENDING
        );

        when(employeeRepository.getByUsername("frodo"))
                .thenReturn(employee);

        when(taskRepository.getAllTasks(1L, PageRequest.of(0, 20)))
                .thenReturn(List.of(dto));

        List<TaskRespDto> response =
                taskService.getAllTasks("frodo", 0, 20);

        assertEquals(1, response.size());
        assertEquals(1L, response.get(0).id());
        assertEquals("Complete Task 1", response.get(0).title());

        verify(employeeRepository).getByUsername("frodo");
        verify(taskRepository).getAllTasks(
                1L, PageRequest.of(0, 20));
    }

    @Test
    void addTaskTest() {
        User user = new User("frodo", "frodo@123", Role.EMPLOYEE);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("frodo baggins");
        employee.setUser(user);

        TaskDto dto = new TaskDto(
                "Complete Task 1",
                "Implement Task 1",
                LocalDate.of(2026, 10, 30),
                Priority.HIGH,
                Status.PENDING
        );

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        taskService.addTask(dto, 1L);

        verify(employeeRepository).findById(1L);
        verify(taskRepository).save(any(Task.class));

    }

    @Test
    void updateStatusTest() {
        User user = new User("frodo", "frodo@123", Role.EMPLOYEE);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("frodo baggins");
        employee.setUser(user);

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Complete Task 1");
        task.setStatus(Status.PENDING);
        task.setEmployee(employee);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        taskService.updateStatus(1L, Status.INPROGRESS, "frodo");

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(task);
    }

    @Test
    void deleteTaskTest() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Complete Task 1");

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        taskService.deleteTask(1L);

        verify(taskRepository).findById(1L);
        verify(taskRepository).delete(task);
    }
}
