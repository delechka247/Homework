package ru.kpfu.itis.transportsem.services;

public interface JwtBlacklistService {
    void add(String token);

    boolean exists(String token);
}