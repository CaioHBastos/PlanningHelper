package com.bswork.helper.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDomain {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String newPassword;
    private String question;
    private String answer;
}
