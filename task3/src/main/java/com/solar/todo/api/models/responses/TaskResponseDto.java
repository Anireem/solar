package com.solar.todo.api.models.responses;

import com.solar.todo.dao.constants.TaskStatus;
import com.solar.todo.dao.entities.TaskEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * A DTO for the {@link TaskEntity} entity
 */
public record TaskResponseDto(
    Long id,

    @Schema(description = "Task status", example = "DRAFT")
    TaskStatus status,

    @Schema(description = "Expiration date")
    LocalDateTime expiration,

    @Schema(description = "Comment", example = "Do your homework")
    String comment,

    @Schema(description = "Additional data sheet")
    Set<SubtaskResponseDto> subtaskEntities
) implements Serializable {
}