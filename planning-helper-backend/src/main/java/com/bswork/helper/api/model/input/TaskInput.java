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

    @NotNull
    private Long hours;

    @NotNull
    private Integer issueId;

    @NotBlank
    private String epicLink;

    @NotBlank
    private String complexityPoints;

    @NotBlank
    private String priority;

    @NotBlank
    private String components;

    @NotBlank
    private String fixVersions;

    @NotBlank
    private String labels;

    @NotBlank
    private String dueDate;

    @NotBlank
    private String team;

    @NotNull
    private Long originalEstimate;

    @NotNull
    @Valid
    private StoryIdInput story;
}
