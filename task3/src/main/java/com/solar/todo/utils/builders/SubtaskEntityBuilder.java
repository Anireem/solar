package com.solar.todo.utils.builders;

import com.solar.todo.dao.entities.SubtaskEntity;

import java.time.LocalDateTime;

public final class SubtaskEntityBuilder {
    private String description;
    private Boolean closed;
    private LocalDateTime expiration;

    private SubtaskEntityBuilder() {
    }

    public static SubtaskEntityBuilder aSubtaskEntity() {
        return new SubtaskEntityBuilder();
    }

    public SubtaskEntityBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public SubtaskEntityBuilder withClosed(Boolean closed) {
        this.closed = closed;
        return this;
    }

    public SubtaskEntityBuilder withExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
        return this;
    }

    public SubtaskEntity build() {
        SubtaskEntity subtaskEntity = new SubtaskEntity();
        subtaskEntity.setDescription(description);
        subtaskEntity.setClosed(closed);
        subtaskEntity.setExpiration(expiration);
        return subtaskEntity;
    }
}
