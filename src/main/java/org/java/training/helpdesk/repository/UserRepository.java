package org.java.training.helpdesk.repository;

import org.java.training.helpdesk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByActivationCode(String code);
}