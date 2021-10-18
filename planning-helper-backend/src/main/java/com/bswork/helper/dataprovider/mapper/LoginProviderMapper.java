package com.bswork.helper.dataprovider.mapper;

import com.bswork.helper.dataprovider.model.Login;
import com.bswork.helper.domain.model.LoginDomain;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginProviderMapper {

    private final ModelMapper modelMapper;

    public Login toEntity(LoginDomain loginDomain) {
        return modelMapper.map(loginDomain, Login.class);
    }
}
