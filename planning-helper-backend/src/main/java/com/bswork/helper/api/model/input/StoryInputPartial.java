package com.bswork.helper.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryInputPartial {

    private String title;
    private String storyNumber;
    private UserIdInput user;
}
