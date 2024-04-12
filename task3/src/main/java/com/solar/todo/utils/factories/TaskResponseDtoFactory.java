package com.solar.todo.utils.factories;

import com.solar.todo.api.models.responses.TaskResponseDto;
import com.solar.todo.dao.entities.TaskEntity;
import com.solar.todo.utils.builders.TaskResponseDtoBuilder;

import java.util.stream.Collectors;

public final class TaskResponseDtoFactory {
    private TaskResponseDtoFactory() {
    }

    public static final TaskResponseDto make(TaskEntity taskEntity) {
        return TaskResponseDtoBuilder.aContractResponseDto()
            .withId(taskEntity.getId())
            .withStatus(taskEntity.getStatus())
            .withExpiration(taskEntity.getExpiration())
            .withComment(taskEntity.getComment())
            .withSubtaskResponseDtoSet(taskEntity.getSubtaskEntities()
                .stream()
                .map(SubtaskResponseDtoFactory::create)
                .collect(Collectors.toSet())
            ).build();
    }
}
