package com.bswork.helper.domain.gateway;

import com.bswork.helper.domain.model.UserDomain;

import java.util.List;
import java.util.Optional;

public interface UserGateway {

    UserDomain saveUser(UserDomain userDomain);
    Optional<UserDomain> findByEmail(String email);
    List<UserDomain> getAll();
    UserDomain getByIdUser(Long id);
    void deleteById(Long id);
    UserDomain updatePartial(UserDomain userDadosActual);
    UserDomain findByQuestionAndAnswer(String question, String answer);
}
