package com.bswork.helper.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginInput {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
