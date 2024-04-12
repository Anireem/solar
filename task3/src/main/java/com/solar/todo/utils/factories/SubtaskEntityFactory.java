package com.solar.todo.utils.factories;

import com.solar.todo.api.models.requests.SubtaskRequestDto;
import com.solar.todo.dao.entities.SubtaskEntity;
import com.solar.todo.utils.builders.SubtaskEntityBuilder;

public final class SubtaskEntityFactory {
    private SubtaskEntityFactory() {
    }

    public static SubtaskEntity create(
        SubtaskRequestDto subtaskRequestDto
    ) {
        return SubtaskEntityBuilder.aSubtaskEntity()
            .withDescription(subtaskRequestDto.description())
            .withClosed(subtaskRequestDto.closed())
            .withExpiration(subtaskRequestDto.expiration())
            .build();
    }
}
