package com.solar.todo.services;

import com.solar.todo.api.models.requests.TaskRequestDto;
import com.solar.todo.api.models.responses.TaskResponseDto;
import com.solar.todo.dao.constants.TaskStatus;
import com.solar.todo.dao.entities.SubtaskEntity;
import com.solar.todo.dao.entities.TaskEntity;
import com.solar.todo.dao.repositories.SubtaskRepository;
import com.solar.todo.dao.repositories.TaskRepository;
import com.solar.todo.utils.factories.TaskEntityFactory;
import com.solar.todo.utils.factories.TaskResponseDtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Represents service layer for working with Tasks.
 */
@Service
public class TaskService {
    /**
     * Repository for working with Tasks.
     */
    private TaskRepository taskRepository;
    /**
     * Repository for working with SubTasks.
     */
    private SubtaskRepository subtaskRepository;
    private static final Logger log = LoggerFactory.getLogger(TaskService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * Constructor, parameters are filled using Dependency Injection.
     * @param taskRepository Repository for working with Tasks.
     * @param subTaskRepository Repository for working with subTasks.
     */
    @Autowired
    public TaskService(final TaskRepository taskRepository,
                       final SubtaskRepository subTaskRepository) {
        this.taskRepository = taskRepository;
        this.subtaskRepository = subTaskRepository;
    }

    /**
     * Adds Task to database.
     * @param taskRequestDto DTO for Task creation.
     * @return DTO of a newly created Task.
     */
    public TaskResponseDto
    addContract(final TaskRequestDto taskRequestDto) {
        TaskEntity taskEntity = taskRepository.save(
            TaskEntityFactory.create(taskRequestDto)
        );
        return TaskResponseDtoFactory.make(taskEntity);
    }

    /**
     * Retrieves Task from database by id.
     * @param id Task ID.
     * @return Found Task DTO.
     */
    public TaskResponseDto getTaskById(final Long id) {
        TaskEntity taskEntity = taskRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        return TaskResponseDtoFactory.make(taskEntity);
    }

    /**
     * Returns page of Tasks as list of Task response DTOs.
     * @param pageable consists page number, size and sort type.
     * @return list of Task response DTOs.
     */
    public List<TaskResponseDto> getTasks(
        final Pageable pageable
    ) {
        return retrieveTasks(pageable)
            .getContent()
            .stream()
            .map(TaskResponseDtoFactory::make)
            .toList();
    }

    /**
     * Remove Task from database if it exists.
     * @param id ID of the Task to be deleted.
     */
    public void deleteTask(final Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(NOT_FOUND,
                String.format("Entity: %s not found", 1L));
        }
    }

    /**
     * Close next subtask of current task,
     * Stop Task if all Subtasks closed.
     * @param id task ID.
     */
    public void closeNextSubtaskOfCurrentTask(final Long id) {
        TaskEntity taskEntity = taskRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));

        List<SubtaskEntity> openedSubtaskEntities = taskEntity.getSubtaskEntities()
            .stream()
            .filter(subtaskEntity -> !subtaskEntity.getClosed())
            .toList();
        if (openedSubtaskEntities.size() == 1) {
            stopTask(taskEntity);
        }
        openedSubtaskEntities.get(0).setClosed(true);
        taskRepository.save(taskEntity);
    }

    /**
     * Set state of current task.
     * @param id task ID.
     * @param state new task state.
     * @return ResponseEntity with code 204, without extra content.
     */
    public void setTaskState(Long id, String state) {
        TaskEntity taskEntity = taskRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        taskEntity.setStatus(TaskStatus.valueOf(state));
        taskRepository.save(taskEntity);
    }

    //region Private methods
    private Page<TaskEntity> retrieveTasks(final Pageable pageable) {
        try {
            return taskRepository.findAll(pageable);
        } catch (PropertyReferenceException propertyReferenceException) {
            throw new ResponseStatusException(
                BAD_REQUEST,
                "Error retrieving tasks",
                propertyReferenceException);
        }
    }

    private void stopTask(TaskEntity taskEntity) {
        taskEntity.setStatus(TaskStatus.STOPPED);
        taskRepository.save(taskEntity);
    }

    @Scheduled(fixedRate = 5000)
    private void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        List<SubtaskEntity> expiredSubtasks = subtaskRepository.findByExpirationBefore(LocalDateTime.now());
        expiredSubtasks.forEach(subtaskEntity -> {
            subtaskEntity.setClosed(true);
            subtaskRepository.save(subtaskEntity);
        });
    }
    //endregion
}
