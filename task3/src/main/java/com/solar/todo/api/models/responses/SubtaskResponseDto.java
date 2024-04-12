package com.solar.todo.api.models.responses;

import com.solar.todo.dao.entities.SubtaskEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link SubtaskEntity} entity
 */
public record SubtaskResponseDto(
    @Schema(description = "Task ID", example = "12")
    Long id,

    @Schema(description = "Description", example = "Task description")
    String description,

    @Schema(description = "Subtask status", example = "false")
    Boolean closed,

    @Schema(description = "Expiration date", example = "false")
    LocalDateTime expiration
) {
}
