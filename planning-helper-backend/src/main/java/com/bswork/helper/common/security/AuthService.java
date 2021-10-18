package com.bswork.helper.common.security;

import com.bswork.helper.dataprovider.exception.UserNotFoundException;
import com.bswork.helper.dataprovider.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("O usuário com o email '%s' não foi encontrado, nos registros", username)));
    }
}
