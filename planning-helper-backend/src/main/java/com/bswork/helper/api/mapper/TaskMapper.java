package com.bswork.helper.api.mapper;

import com.bswork.helper.api.model.dto.TaskDto;
import com.bswork.helper.api.model.input.TaskInput;
import com.bswork.helper.api.model.input.TaskInputPartial;
import com.bswork.helper.domain.model.TaskDomain;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class TaskMapper {

    private final ModelMapper modelMapper;

    public TaskDto toDto(TaskDomain taskDomain) {
        return modelMapper.map(taskDomain, TaskDto.class);
    }

    public List<TaskDto> toCollectionDto(List<TaskDomain> tasksDomain) {
        return tasksDomain.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TaskDomain toDomain(TaskInput taskInput) {
        return modelMapper.map(taskInput, TaskDomain.class);
    }

    public TaskDomain toDomainPartial(TaskInputPartial taskInputPartial) {
        return modelMapper.map(taskInputPartial, TaskDomain.class);
    }
}
