package ru.itis.javalab.statementsservice.services;

public interface JwtBlacklistService {
    void add(String token);
    boolean exists(String token);
}