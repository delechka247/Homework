package ru.itis.javalab.statementsservice.repositories;

public interface BlacklistRepository {
    void save(String token);
    boolean exists(String token);
}
