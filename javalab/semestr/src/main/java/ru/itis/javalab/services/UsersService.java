package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.UUID;

public interface UsersService {
    List<UserDto> getAllUsers();
    UserDto getUser(Long userId);
    void confirmEmail(UUID confirmCode);
    void banAll();
}
