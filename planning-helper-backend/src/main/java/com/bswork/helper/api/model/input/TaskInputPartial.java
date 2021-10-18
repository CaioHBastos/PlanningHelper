package com.bswork.helper.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInputPartial {

    private String issueType;
    private String description;
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
}
