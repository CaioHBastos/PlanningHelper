package com.bswork.helper.api.mapper;

import com.bswork.helper.api.model.dto.TokenDto;
import com.bswork.helper.api.model.dto.UserDto;
import com.bswork.helper.api.model.input.LoginInput;
import com.bswork.helper.domain.model.LoginDomain;
import com.bswork.helper.domain.model.TokenDomain;
import com.bswork.helper.domain.model.UserDomain;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginMapper {

    private final ModelMapper modelMapper;

    public static UsernamePasswordAuthenticationToken toUserPassword(LoginInput loginInput) {
        return new UsernamePasswordAuthenticationToken(loginInput.getEmail(), loginInput.getPassword());
    }

    public LoginDomain toDomain(LoginInput login) {
        return modelMapper.map(login, LoginDomain.class);
    }

    public UserDto toDto(UserDomain userDomain) {
        return modelMapper.map(userDomain, UserDto.class);
    }

    public TokenDto toDtoToken(TokenDomain tokenDomain) {
        return modelMapper.map(tokenDomain, TokenDto.class);
    }
}
