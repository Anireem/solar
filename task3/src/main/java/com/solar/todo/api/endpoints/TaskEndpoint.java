package com.solar.todo.api.endpoints;

import com.solar.todo.api.models.requests.TaskRequestDto;
import com.solar.todo.api.models.responses.TaskResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Represents the endpoint for working with Tasks.
 */
@Tag(
    name = "Endpoint for working with Tasks",
    description = "REST API to save and retrieve tasks data."
)
public interface TaskEndpoint {
    /**
     * [POST] /api/tasks
     */
    @Operation(summary = "Adds Task to database.")
    @ApiResponse(
        responseCode = "201",
        description = "Success, task successfully received."
    )
    @ApiResponse(
        responseCode = "401",
        description = "Bad request, incorrect body format."
    )
    ResponseEntity<Void> addTask(
        @RequestBody @Valid TaskRequestDto taskRequestDto,
        UriComponentsBuilder ucb
    );

    /**
     * [GET] /api/tasks/{id}
     */
    @Operation(summary = "Returns Task by ID.")
    @ApiResponse(
        responseCode = "200",
        description = "Success - Task successfully received.",
        content = {@Content(
            schema = @Schema(implementation = TaskResponseDto.class)
        )}
    )
    @ApiResponse(
        responseCode = "403",
        description = "Error - there is no Task with this ID in the"
            + " database.",
        content = @Content
    )
    ResponseEntity<TaskResponseDto> getTask(@PathVariable(value = "id") Long id);

    /**
     * [GET] /api/tasks
     */
    @Operation(summary = "Returns all Tasks.")
    @ApiResponse(
        responseCode = "200",
        description = "Success - Tasks successfully received."
    )
    ResponseEntity<Iterable<TaskResponseDto>> getTasks(
        @ParameterObject Pageable pageable
    );

    /**
     * [DELETE] /api/tasks/{id}
     */
    @Operation(summary = "Delete Task.")
    @ApiResponse(
        responseCode = "204",
        description = "No content - Task successfully deleted."
    )
    @ApiResponse(
        responseCode = "404",
        description = "Not found - there is no Task with this ID"
            + "in the database."
    )
    ResponseEntity<Void> deleteTask(@PathVariable(value = "id") Long id);

    /**
     * [PATCH] /api/tasks/{id}/close_current_subtask
     */
    @Operation(summary = "Close next Subtask of current Task.")
    @ApiResponse(
        responseCode = "204",
        description = "No content - Task successfully updated."
    )
    @ApiResponse(
        responseCode = "404",
        description = "Not found - there is no Task with this ID"
            + "in the database."
    )
    ResponseEntity<Void> closeNextSubtaskOfCurrentTask(@PathVariable(value = "id") Long id);

    /**
     * [POST] /api/tasks/{id}/set_task_state
     */
    @Operation(summary = "Close next Subtask of current Task.")
    @ApiResponse(
        responseCode = "204",
        description = "No content - Task successfully updated."
    )
    @ApiResponse(
        responseCode = "404",
        description = "Not found - there is no Task with this ID"
            + "in the database."
    )
    ResponseEntity<Void> setTaskState(@PathVariable(value = "id") Long id,
                                      @RequestParam String state);
}
