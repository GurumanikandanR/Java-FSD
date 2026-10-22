package com.springboot.case_study_evaluation.repository;

import com.springboot.case_study_evaluation.dto.response.TaskRespDto;
import com.springboot.case_study_evaluation.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("""
    select new com.springboot.case_study_evaluation.dto.response.TaskRespDto(
    t.id,t.title,t.description,t.dueDate,t.priority,t.status)
    FROM Task t
    where t.employee.id=?1
""")
    List<TaskRespDto> getAllTasks(Long id, Pageable pageable);
}
