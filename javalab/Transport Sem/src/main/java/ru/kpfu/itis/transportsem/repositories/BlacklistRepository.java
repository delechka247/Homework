package ru.kpfu.itis.transportsem.repositories;

public interface BlacklistRepository {
    void save(String token);

    boolean exists(String token);
}