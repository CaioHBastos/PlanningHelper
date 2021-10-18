package com.bswork.helper.dataprovider.repository;

import com.bswork.helper.dataprovider.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByQuestionAndAnswer(String question, String answer);
}
