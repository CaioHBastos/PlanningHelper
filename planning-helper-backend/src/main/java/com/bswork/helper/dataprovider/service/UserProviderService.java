package com.bswork.helper.dataprovider.service;

import com.bswork.helper.dataprovider.mapper.UserProviderMapper;
import com.bswork.helper.dataprovider.model.User;
import com.bswork.helper.dataprovider.repository.UserRepository;
import com.bswork.helper.dataprovider.exception.UserNotFoundException;
import com.bswork.helper.domain.gateway.UserGateway;
import com.bswork.helper.domain.model.UserDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class UserProviderService implements UserGateway {

    private final UserProviderMapper userProviderMapper;
    private final UserRepository userRepository;

    @Override
    public List<UserDomain> getAll() {
        List<User> users = userRepository.findAll();
        return userProviderMapper.toCollectionDomain(users);
    }

    @Override
    public UserDomain getByIdUser(Long id) {
        User userPresente = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("O usuário '%s' informado, não está cadastrado", id)));
        return userProviderMapper.toDomain(userPresente);
    }

    @Transactional
    @Override
    public UserDomain saveUser(UserDomain userDomain) {
        User userNew = userProviderMapper.toEntity(userDomain);
        User userCreated = userRepository.save(userNew);
        return userProviderMapper.toDomain(userCreated);
    }

    @Override
    public Optional<UserDomain> findByEmail(String email) {
        Optional<User> userDataPresente = userRepository.findByEmail(email);
        return userProviderMapper.toDomainOptional(userDataPresente);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserDomain updatePartial(UserDomain userDadosActual) {
        User userNew = userProviderMapper.toEntity(userDadosActual);
        User userCreated = userRepository.save(userNew);
        return userProviderMapper.toDomain(userCreated);
    }

    @Override
    public UserDomain findByQuestionAndAnswer(String question, String answer) {
        User user = userRepository.findByQuestionAndAnswer(question, answer)
                .orElseThrow(() -> new UserNotFoundException(
                        "Não foi possível obter os dados do usuário de acordo com a questão e a resposta informada."));

        return userProviderMapper.toDomain(user);
    }
}
