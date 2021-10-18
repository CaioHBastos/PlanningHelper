package com.bswork.helper.api.controller;

import com.bswork.helper.api.mapper.LoginMapper;
import com.bswork.helper.api.model.dto.TokenDto;
import com.bswork.helper.api.model.dto.UserDto;
import com.bswork.helper.api.model.input.LoginInput;
import com.bswork.helper.common.security.TokenService;
import com.bswork.helper.domain.model.UserDomain;
import com.bswork.helper.domain.usecase.UserJourneyUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping
public class LoginController {

    private final LoginMapper loginMapper;
    private final UserJourneyUsecase userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Validated LoginInput loginInput) {
        UsernamePasswordAuthenticationToken dadosLogin = LoginMapper.toUserPassword(loginInput);

        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);
            return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return new ResponseEntity("Usuário ou senha inválidos.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestParam String question, @RequestParam String answer) {
        UserDomain userDomain = userService.forgotPassword(question, answer);
        UserDto userDto = loginMapper.toDto(userDomain);
        return ResponseEntity.ok().body(userDto);
    }
}
