package ru.itis.javalab.statementsservice.services;

import ru.itis.javalab.statementsservice.dto.UserDto;

public interface UsersService {
    UserDto getUserFromJwt(String token);
    UserDto getUserById(Long id);
    void blockUser(Long userId);
}
