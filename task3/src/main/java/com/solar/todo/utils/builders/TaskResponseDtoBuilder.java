package com.solar.todo.utils.builders;

import com.solar.todo.api.models.responses.SubtaskResponseDto;
import com.solar.todo.api.models.responses.TaskResponseDto;
import com.solar.todo.dao.constants.TaskStatus;

import java.time.LocalDateTime;
import java.util.Set;

public final class TaskResponseDtoBuilder {
    private Long id;
    private TaskStatus status;
    private LocalDateTime expiration;
    private String comment;
    private Set<SubtaskResponseDto> subtaskResponseDtoSet;

    private TaskResponseDtoBuilder() {
    }

    public static TaskResponseDtoBuilder aContractResponseDto() {
        return new TaskResponseDtoBuilder();
    }

    public TaskResponseDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public TaskResponseDtoBuilder withStatus(TaskStatus status) {
        this.status = status;
        return this;
    }

    public TaskResponseDtoBuilder withExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
        return this;
    }


    public TaskResponseDtoBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public TaskResponseDtoBuilder withSubtaskResponseDtoSet(Set<SubtaskResponseDto> subtaskResponseDtoSet) {
        this.subtaskResponseDtoSet = subtaskResponseDtoSet;
        return this;
    }

    public TaskResponseDto build() {
        return new TaskResponseDto(id, status, expiration, comment, subtaskResponseDtoSet);
    }
}
