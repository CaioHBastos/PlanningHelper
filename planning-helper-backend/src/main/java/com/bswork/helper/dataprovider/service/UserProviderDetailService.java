package com.bswork.helper.dataprovider.service;

import com.bswork.helper.dataprovider.model.User;
import com.bswork.helper.dataprovider.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Component
public class UserProviderDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if(Objects.isNull(user)){
            throw new UsernameNotFoundException(email);
        }

        return user.get();
    }
}
