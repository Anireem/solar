package com.solar.todo.dao.repositories;

import com.solar.todo.dao.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository
    extends
        JpaRepository<TaskEntity, Long>,
        PagingAndSortingRepository<TaskEntity, Long> {
}
