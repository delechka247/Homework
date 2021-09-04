package ru.kpfu.itis.transportsem.services;

import ru.kpfu.itis.transportsem.dto.UserDto;
import ru.kpfu.itis.transportsem.validation.ValidNames;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();
    UserDto getUserFromJwt(String token);
    void blockUser(Long userId);
}
