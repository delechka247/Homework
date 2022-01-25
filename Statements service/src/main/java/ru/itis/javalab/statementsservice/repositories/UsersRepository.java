package ru.itis.javalab.statementsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.statementsservice.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(Long id);

}
