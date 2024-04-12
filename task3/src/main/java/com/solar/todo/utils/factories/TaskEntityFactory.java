package com.solar.todo.utils.factories;

import com.solar.todo.api.models.requests.SubtaskRequestDto;
import com.solar.todo.api.models.requests.TaskRequestDto;
import com.solar.todo.dao.entities.SubtaskEntity;
import com.solar.todo.dao.entities.TaskEntity;
import com.solar.todo.utils.builders.TaskEntityBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class TaskEntityFactory {

    private TaskEntityFactory() {
    }

    public static TaskEntity create(TaskRequestDto taskRequestDto) {
        List<SubtaskRequestDto> subtaskRequestDtoSet = Optional
            .ofNullable(taskRequestDto.subtaskRequestDtoList())
            .orElse(new ArrayList<>());

        List<SubtaskEntity> subtaskEntities = subtaskRequestDtoSet
            .stream()
            .map(SubtaskEntityFactory::create)
            .toList();

        TaskEntity taskEntity = TaskEntityBuilder.aContractEntity()
            .withStatus(taskRequestDto.status())
            .withExpiration(taskRequestDto.expiration())
            .withComment(taskRequestDto.comment())
            .withSubtaskEntities(subtaskEntities)
            .build();

        taskEntity.getSubtaskEntities().forEach(subtaskEntity ->
            subtaskEntity.setContractEntity(taskEntity));

        return taskEntity;
    }
}
