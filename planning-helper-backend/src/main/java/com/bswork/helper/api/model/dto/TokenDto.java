package com.bswork.helper.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class TokenDto {

    private String token;
    private String type;
}
