package ru.kpfu.itis.transportsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.transportsem.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}