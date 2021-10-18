package com.bswork.helper.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StoryInput {

    @NotBlank
    private String title;

    @NotBlank
    private String storyNumber;

    @NotNull
    @Valid
    private UserIdInput user;
}
