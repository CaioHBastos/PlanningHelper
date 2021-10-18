package com.bswork.helper.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StoryDto {

    private String id;
    private String title;
    private String storyNumber;
    private UserDto user;
    private List<TaskDto> task;
}
