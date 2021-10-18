package com.bswork.helper.api.controller;

import com.bswork.helper.api.constants.UriConstants;
import com.bswork.helper.api.mapper.TaskMapper;
import com.bswork.helper.api.model.dto.TaskDto;
import com.bswork.helper.api.model.input.TaskInput;
import com.bswork.helper.api.model.input.TaskInputPartial;
import com.bswork.helper.domain.model.TaskDomain;
import com.bswork.helper.domain.usecase.TaskJourneyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping(UriConstants.URI_BASE_TASK)
public class TaskController {

    private final TaskMapper taskMapper;
    private final TaskJourneyService taskJourneyService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam(value = "story_number", required = false) Optional<String> jiraKey,
                                                     @RequestParam(value = "user_id", required = false) Optional<Long> userId) {

        List<TaskDomain> tasksDomain = taskJourneyService.getAllTasksByKeyOrId(jiraKey, userId);
        List<TaskDto> tasksDtop = taskMapper.toCollectionDto(tasksDomain);

        return ResponseEntity.ok(tasksDtop);
    }

    @GetMapping(UriConstants.URI_BASE_TASK_ID)
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {

        TaskDomain taskDomain = taskJourneyService.getTaskById(id);
        TaskDto taskDto = taskMapper.toDto(taskDomain);

        return ResponseEntity.ok(taskDto);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody @Validated TaskInput taskInput) {

        TaskDomain taskDomainCreate = taskMapper.toDomain(taskInput);
        TaskDomain tasDomainCreated = taskJourneyService.createTask(taskDomainCreate);
        TaskDto taskDtoCreated = taskMapper.toDto(tasDomainCreated);

        return new ResponseEntity<>(taskDtoCreated, HttpStatus.CREATED);
    }

    @PutMapping(UriConstants.URI_BASE_TASK_ID)
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskInputPartial taskInputPartial) {

        TaskDomain taskDomainUpdate = taskMapper.toDomainPartial(taskInputPartial);
        TaskDomain taskDomainUpdated = taskJourneyService.taskUpdate(id, taskDomainUpdate);
        TaskDto taskDtoUpdated = taskMapper.toDto(taskDomainUpdated);

        return ResponseEntity.ok(taskDtoUpdated);
    }

    @DeleteMapping(UriConstants.URI_BASE_TASK_ID)
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskJourneyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
