package com.solar.todo.controllers;

import com.solar.todo.api.constants.ApiRoutes;
import com.solar.todo.api.endpoints.TaskEndpoint;
import com.solar.todo.api.models.requests.TaskRequestDto;
import com.solar.todo.api.models.responses.TaskResponseDto;
import com.solar.todo.errors.handlers.ControllerExceptionHandler;
import com.solar.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Represents methods for working with tasks.
 */
@RestController
@RequestMapping(ApiRoutes.Task.TASK_CONTEXT_PATH)
public class TaskController
    implements TaskEndpoint, ControllerExceptionHandler {

    /**
     * Service layer for working with Tasks.
     */
    private final TaskService taskService;

    /**
     * Constructor, parameters are filled using Dependency Injection.
     * @param taskService Service layer for working with Tasks.
     */
    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Adds Task to the database.
     * @param taskRequestDto DTO for creating Task.
     * @param ucb Service parameter, filled in automatically,
     * @return ResponseEntity with code 200 (with a link to a new element),
     * or with code 400.
     */
    @PostMapping
    @Override
    public ResponseEntity<Void> addTask(
        final TaskRequestDto taskRequestDto,
        final UriComponentsBuilder ucb
    ) {
        TaskResponseDto taskResponseDto =
            taskService.addContract(taskRequestDto);
        URI location = ucb
            .path(ApiRoutes.Task.TASK_CONTEXT_PATH + "/{id}")
            .buildAndExpand(taskResponseDto.id())
            .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Get Task by ID.
     * @param id Task ID.
     * @return ResponseEntity with code 200 and body containing Task's DTO.
     */
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TaskResponseDto> getTask(final Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    /**
     * Returns page of Tasks as list of Task response DTOs.
     * @param pageable consists page number (0-default), size (20-default
     *                 and sort type (no sort - default).
     * @return list of Task response DTOs.
     */
    @GetMapping
    @Override
    public ResponseEntity<Iterable<TaskResponseDto>>
    getTasks(final Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasks(pageable));
    }

    /**
     * Delete task by ID from database.
     * @param id task ID.
     * @return ResponseEntity with code 204, without extra content.
     */
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteTask(final Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Close next subtask of current task.
     * @param id task ID.
     * @return ResponseEntity with code 204, without extra content.
     */
    @PatchMapping("/{id}/close_current_subtask")
    @Override
    public ResponseEntity<Void> closeNextSubtaskOfCurrentTask(final Long id) {
        taskService.closeNextSubtaskOfCurrentTask(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Set state of current task.
     * @param id task ID.
     * @param state new task state.
     * @return ResponseEntity with code 204, without extra content.
     */
    @PatchMapping("/{id}/set_task_state")
    @Override
    public ResponseEntity<Void> setTaskState(Long id, String state) {
        taskService.setTaskState(id, state);
        return ResponseEntity.noContent().build();
    }
}
