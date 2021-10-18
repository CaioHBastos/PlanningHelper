package com.bswork.helper.domain.usecase;

import com.bswork.helper.domain.exception.NoContentTaskException;
import com.bswork.helper.domain.exception.ValueInvalidException;
import com.bswork.helper.domain.gateway.TaskGateway;
import com.bswork.helper.domain.model.TaskDomain;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskJourneyService {

    private final TaskGateway taskGateway;
    private final StoryJourneyUsecase storyJourneyUsecase;

    public List<TaskDomain> getAllTasksByKeyOrId(Optional<String> jiraKey, Optional<Long> userId) {

        List<TaskDomain> tasksDomain = new ArrayList<>();

        if (jiraKey.isEmpty() && userId.isEmpty()) {
            throw new ValueInvalidException("É ncesário informar o número da história ou o id de usuário.");
        }

        if (jiraKey.isPresent()) {
            tasksDomain = taskGateway.findAllByStoryNumber(jiraKey.get());
        }

        if (userId.isPresent()) {
            tasksDomain = taskGateway.findAllByUserId(userId.get());
        }

        verifyListEmpty(tasksDomain);
        return tasksDomain;

    }

    public TaskDomain getTaskById(Long id) {
        return taskGateway.findTaskById(id);
    }

    private void verifyListEmpty(List<TaskDomain> tasksDomain) {
        if (tasksDomain.isEmpty()) {
            throw new NoContentTaskException();
        }
    }

    public TaskDomain createTask(TaskDomain taskDomainCreate) {
        storyJourneyUsecase.getStoryById(taskDomainCreate.getStory().getId());
        return taskGateway.saveTask(taskDomainCreate);
    }

    public void deleteById(Long id) {
        getTaskById(id);
        taskGateway.deleteById(id);
    }

    public TaskDomain taskUpdate(Long id, TaskDomain taskDomainUpdate) {
        TaskDomain taskDomainActual = getTaskById(id);

        if (StringUtils.isNotBlank(taskDomainUpdate.getIssueType())) {
            taskDomainActual.setIssueType(taskDomainUpdate.getIssueType());
        }

        if (StringUtils.isNotBlank(taskDomainUpdate.getDescription())) {
            taskDomainActual.setDescription(taskDomainUpdate.getDescription());
        }

        if (StringUtils.isNotBlank(taskDomainUpdate.getSummary())) {
            taskDomainActual.setSummary(taskDomainUpdate.getSummary());
        }

        if (Objects.nonNull(taskDomainUpdate.getHours())) {
            taskDomainActual.setHours(taskDomainUpdate.getHours());
        }

        if (Objects.nonNull(taskDomainUpdate.getIssueId())) {
            taskDomainActual.setIssueId(taskDomainUpdate.getIssueId());
        }

        if (StringUtils.isNotBlank(taskDomainUpdate.getEpicLink())) {
            taskDomainActual.setEpicLink(taskDomainUpdate.getEpicLink());
        }

        if (StringUtils.isNotBlank(taskDomainUpdate.getComplexityPoints())) {
            taskDomainActual.setComplexityPoints(taskDomainUpdate.getComplexityPoints());
        }

        if (StringUtils.isNotBlank(taskDomainUpdate.getPriority())) {
            taskDomainActual.setPriority(taskDomainUpdate.getPriority());
        }

        if (StringUtils.isNotBlank(taskDomainUpdate.getComponents())) {
            taskDomainActual.setComponents(taskDomainUpdate.getComponents());
        }

        if (StringUtils.isNotBlank(taskDomainUpdate.getFixVersions())) {
            taskDomainActual.setFixVersions(taskDomainUpdate.getFixVersions());
        }

        if (StringUtils.isNotBlank(taskDomainUpdate.getLabels())) {
            taskDomainActual.setLabels(taskDomainUpdate.getLabels());
        }

        if (StringUtils.isNotBlank(taskDomainUpdate.getDueDate())) {
            taskDomainActual.setDueDate(taskDomainUpdate.getDueDate());
        }

        if (StringUtils.isNotBlank(taskDomainUpdate.getTeam())) {
            taskDomainActual.setTeam(taskDomainUpdate.getTeam());
        }

        if (Objects.nonNull(taskDomainUpdate.getOriginalEstimate())) {
            taskDomainActual.setOriginalEstimate(taskDomainUpdate.getOriginalEstimate());
        }

        return taskGateway.saveTask(taskDomainActual);
    }
}
