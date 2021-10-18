package com.bswork.helper.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StoryIdInput {

    @NotNull
    private Long id;
}
