package com.solar.todo.utils.factories;

import com.solar.todo.api.models.responses.SubtaskResponseDto;
import com.solar.todo.dao.entities.SubtaskEntity;
import com.solar.todo.utils.builders.SubtaskResponseDtoBuilder;

public final class SubtaskResponseDtoFactory {
    private SubtaskResponseDtoFactory() {
    }

    public static final SubtaskResponseDto create(
        SubtaskEntity subtaskEntity
    ) {
        return SubtaskResponseDtoBuilder.aSubtaskResponseDto()
            .withId(subtaskEntity.getId())
            .withDescription(subtaskEntity.getDescription())
            .withClosed(subtaskEntity.getClosed())
            .withExpiration(subtaskEntity.getExpiration())
            .build();
    }
}
