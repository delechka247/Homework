package ru.itis.javalab.services;

import java.util.UUID;

public interface LoginService {
    String login(String email, String password);
    UUID getUUIDForSession(String email);
}


