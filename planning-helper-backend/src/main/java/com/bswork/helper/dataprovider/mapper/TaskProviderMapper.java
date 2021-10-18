package com.bswork.helper.dataprovider.mapper;

import com.bswork.helper.dataprovider.model.Task;
import com.bswork.helper.domain.model.TaskDomain;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class TaskProviderMapper {

    private final ModelMapper modelMapper;

    public TaskDomain toDomain(Task task) {
        return modelMapper.map(task, TaskDomain.class);
    }

    public List<TaskDomain> toCollectionDomain(List<Task> tasks) {
        return tasks.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public Task toEntity(TaskDomain taskDomainCreate) {
        return modelMapper.map(taskDomainCreate, Task.class);
    }
}
