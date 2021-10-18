package com.bswork.helper.dataprovider.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class Login {

    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken converte() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
