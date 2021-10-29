package com.bswork.helper.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskInput {

    @NotBlank
    private String issueType;

    @NotBlank
    private String description;

    @NotBlank
    private String summary;

    private Long hours;

    private Integer issueId;

    private String epicLink;

    private String complexityPoints;

    private String priority;

    private String components;

    private String fixVersions;

    private String labels;

    private String dueDate;

    private String team;

    private Long originalEstimate;

    @NotNull
    @Valid
    private StoryIdInput story;
}
