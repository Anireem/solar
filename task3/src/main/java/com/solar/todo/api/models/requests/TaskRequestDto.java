package com.solar.todo.api.models.requests;

import com.solar.todo.dao.constants.TaskStatus;
import com.solar.todo.dao.entities.TaskEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A DTO for the {@link TaskEntity} entity
 */
public record TaskRequestDto(
    @Schema(description = "Task status", example = "DRAFT")
    TaskStatus status,

    @Schema(description = "Expiration date")
    LocalDateTime expiration,

    @Schema(description = "Comment", example = "Do you homework")
    String comment,

    @Schema(description = "Additional data sheet")
    List<SubtaskRequestDto> subtaskRequestDtoList
) implements Serializable {
}