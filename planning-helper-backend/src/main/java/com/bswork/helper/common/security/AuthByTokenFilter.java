package com.bswork.helper.common.security;

import com.bswork.helper.dataprovider.model.User;
import com.bswork.helper.dataprovider.repository.UserRepository;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class AuthByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public AuthByTokenFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);

        if (valido) {
            autenticarCliente(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Optional<User> usuario = userRepository.findById(idUsuario);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (Objects.isNull(token) || StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }
}
