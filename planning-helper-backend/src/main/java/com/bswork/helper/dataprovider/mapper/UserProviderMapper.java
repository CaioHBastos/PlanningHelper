package com.bswork.helper.dataprovider.mapper;

import com.bswork.helper.dataprovider.model.User;
import com.bswork.helper.domain.model.UserDomain;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserProviderMapper {

    private final ModelMapper modelMapper;

    public List<UserDomain> toCollectionDomain(List<User> users) {
        return users.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public User toEntity(UserDomain userDomain) {
        return modelMapper.map(userDomain, User.class);
    }

    public UserDomain toDomain(User userCreated) {
        return modelMapper.map(userCreated, UserDomain.class);
    }

    public Optional<UserDomain> toDomainOptional(Optional<User> userDataPresente) {
        return modelMapper.map(userDataPresente, Optional.class);
    }
}
