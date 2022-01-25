package ru.itis.javalab.statementsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.statementsservice.models.RefreshToken;
import ru.itis.javalab.statementsservice.models.User;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByValue(String value);
    Optional<RefreshToken> findByUser(User user);
    void deleteByValue(String value);
}
