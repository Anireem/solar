package com.solar.todo.utils.builders;

import com.solar.todo.dao.constants.TaskStatus;
import com.solar.todo.dao.entities.SubtaskEntity;
import com.solar.todo.dao.entities.TaskEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class TaskEntityBuilder {
    private Long id;
    private TaskStatus status;
    private LocalDateTime expiration;
    private String comment;
    private List<SubtaskEntity> subtaskEntities = new ArrayList<>();

    private TaskEntityBuilder() {
    }

    public static TaskEntityBuilder aContractEntity() {
        return new TaskEntityBuilder();
    }

    public TaskEntityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public TaskEntityBuilder withStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
        return this;
    }

    public TaskEntityBuilder withExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
        return this;
    }

    public TaskEntityBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public TaskEntityBuilder withSubtaskEntities(List<SubtaskEntity> subtaskEntities) {
        this.subtaskEntities = subtaskEntities;
        return this;
    }

    public TaskEntity build() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(id);
        taskEntity.setStatus(status);
        taskEntity.setExpiration(expiration);
        taskEntity.setComment(comment);
        taskEntity.setSubtaskEntities(subtaskEntities);
        return taskEntity;
    }
}
