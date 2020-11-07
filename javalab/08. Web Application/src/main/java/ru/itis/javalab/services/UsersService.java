package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersService {
    List<User> getAllUsersByAge(int age);
    List<User> getOneByUUID(UUID uuid);
    
}

