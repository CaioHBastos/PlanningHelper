package com.bswork.helper.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoryDomain {

    private Long id;
    private String title;
    private String storyNumber;
    private UserDomain user;
    private List<TaskDomain> task;
}
