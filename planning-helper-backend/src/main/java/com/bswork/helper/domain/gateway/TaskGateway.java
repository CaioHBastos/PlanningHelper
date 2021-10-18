package com.bswork.helper.domain.gateway;

import com.bswork.helper.domain.model.TaskDomain;

import java.util.List;

public interface TaskGateway {

    List<TaskDomain> findAllByStoryNumber(String jiraKey);
    List<TaskDomain> findAllByUserId(Long userId);
    TaskDomain saveTask(TaskDomain taskDomainCreate);
    TaskDomain findTaskById(Long id);
    void deleteById(Long id);
    void deleteAllByStoryNumber(String storyNumber);
}
