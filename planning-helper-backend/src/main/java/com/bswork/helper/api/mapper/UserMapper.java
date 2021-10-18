package com.bswork.helper.api.mapper;

import com.bswork.helper.api.model.dto.UserDto;
import com.bswork.helper.api.model.input.UserInput;
import com.bswork.helper.api.model.input.UserInputPartial;
import com.bswork.helper.api.model.input.UserInputPassword;
import com.bswork.helper.domain.model.UserDomain;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDomain toDomain(UserInput userInput) {
        return modelMapper.map(userInput, UserDomain.class);
    }

    public UserDto toDto(UserDomain userDomain) {
        return modelMapper.map(userDomain, UserDto.class);
    }

    public List<UserDto> toCollectionDto(List<UserDomain> usersDomain) {
        return usersDomain.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserDomain toDomainPartial(UserInputPartial userInputPartial) {
        return modelMapper.map(userInputPartial, UserDomain.class);
    }

    public UserDomain toDomainPassword(UserInputPassword userInputPassword) {
        return modelMapper.map(userInputPassword, UserDomain.class);
    }
}
