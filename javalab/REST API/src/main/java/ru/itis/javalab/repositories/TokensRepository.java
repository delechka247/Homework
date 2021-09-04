package ru.itis.javalab.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Token;

import java.util.Optional;

public interface TokensRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}
