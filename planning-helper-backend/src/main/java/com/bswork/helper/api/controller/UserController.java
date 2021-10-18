package com.bswork.helper.api.controller;

import com.bswork.helper.api.constants.UriConstants;
import com.bswork.helper.api.mapper.UserMapper;
import com.bswork.helper.api.model.dto.UserDto;
import com.bswork.helper.api.model.input.UserInput;
import com.bswork.helper.api.model.input.UserInputPartial;
import com.bswork.helper.api.model.input.UserInputPassword;
import com.bswork.helper.domain.model.UserDomain;
import com.bswork.helper.domain.usecase.UserJourneyUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping(UriConstants.URI_BASE_USER)
public class UserController {

    private final UserMapper userMapper;
    private final UserJourneyUsecase userJourneyUsecase;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDomain> usersDomain = userJourneyUsecase.getAllUsers();
        List<UserDto> usersDto = userMapper.toCollectionDto(usersDomain);
        return ResponseEntity.ok(usersDto);
    }

    @GetMapping(UriConstants.URI_BASE_USER_ID)
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserDomain userDomain = userJourneyUsecase.getByIdUser(id);
        UserDto userDto = userMapper.toDto(userDomain);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserInput userInput) {
        UserDomain userNew = userMapper.toDomain(userInput);
        UserDomain userDomainCreated = userJourneyUsecase.createUser(userNew);
        UserDto usetDtoCreated = userMapper.toDto(userDomainCreated);

        return new ResponseEntity<>(usetDtoCreated, HttpStatus.CREATED);
    }

    @PutMapping(UriConstants.URI_BASE_USER_ID)
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserInputPartial userInputPartial) {
        UserDomain userNewPartial = userMapper.toDomainPartial(userInputPartial);
        UserDomain userDomainUpdated = userJourneyUsecase.updateUserPartial(id, userNewPartial);
        UserDto userDtoUptaded = userMapper.toDto(userDomainUpdated);

        return ResponseEntity.ok(userDtoUptaded);
    }

    @PutMapping(UriConstants.URI_BASE_USER_PASSWORD)
    public ResponseEntity<UserDto> updateUserPassword(@PathVariable Long id, @RequestBody UserInputPassword userInputPassword) {
        UserDomain userNewPassword = userMapper.toDomainPassword(userInputPassword);
        UserDomain userDomainUpdated = userJourneyUsecase.updateUserUpdatePassword(id, userNewPassword);
        UserDto userDtoUptaded = userMapper.toDto(userDomainUpdated);

        return ResponseEntity.ok(userDtoUptaded);
    }

    @DeleteMapping(UriConstants.URI_BASE_USER_ID)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userJourneyUsecase.deleteByIdUser(id);
        return ResponseEntity.noContent().build();
    }
}
