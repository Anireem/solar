package com.solar.todo.api.models.requests;

import com.solar.todo.dao.entities.SubtaskEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link SubtaskEntity} entity
 */
public record SubtaskRequestDto(
    @Schema(description = "Description", example = "Task description")
    String description,

    @Schema(description = "Subtask status", example = "false")
    Boolean closed,

    @Schema(description = "Expiration date", example = "false")
    LocalDateTime expiration
) {
}
