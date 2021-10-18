package com.bswork.helper.dataprovider.service;

import com.bswork.helper.dataprovider.exception.TaskNotFoundException;
import com.bswork.helper.dataprovider.mapper.TaskProviderMapper;
import com.bswork.helper.dataprovider.model.Task;
import com.bswork.helper.dataprovider.repository.TaskRepository;
import com.bswork.helper.domain.gateway.TaskGateway;
import com.bswork.helper.domain.model.TaskDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Component
public class TaskProviderService implements TaskGateway {

    private final TaskProviderMapper taskProviderMapper;
    private final TaskRepository taskRepository;

    @Override
    public List<TaskDomain> findAllByStoryNumber(String jiraKey) {
        List<Task> tasks = taskRepository.findAllByStory_storyNumber(jiraKey);
        return taskProviderMapper.toCollectionDomain(tasks);
    }

    @Override
    public List<TaskDomain> findAllByUserId(Long userId) {
        List<Task> tasks = taskRepository.findAllByStory_UserId(userId);
        return taskProviderMapper.toCollectionDomain(tasks);
    }

    @Transactional
    @Override
    public TaskDomain saveTask(TaskDomain taskDomainCreate) {
        Task taskCreate = taskProviderMapper.toEntity(taskDomainCreate);
        Task taskCreated = taskRepository.save(taskCreate);

        return taskProviderMapper.toDomain(taskCreated);
    }

    @Override
    public TaskDomain findTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        String.format("O id '%s' da task informada não existe.", id)));

        return taskProviderMapper.toDomain(task);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllByStoryNumber(String storyNumber) {
        taskRepository.deleteAllByStory_storyNumber(storyNumber);
    }
}
