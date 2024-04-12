package com.solar.todo.dao.repositories;

import com.solar.todo.dao.entities.SubtaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SubtaskRepository extends JpaRepository<SubtaskEntity, Long> {
    List<SubtaskEntity> findByExpirationBefore(LocalDateTime localDateTime);
}
