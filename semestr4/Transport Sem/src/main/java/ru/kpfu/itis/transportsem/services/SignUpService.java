package ru.kpfu.itis.transportsem.services;

import ru.kpfu.itis.transportsem.dto.UserDto;
import ru.kpfu.itis.transportsem.dto.UserForm;

public interface SignUpService{
    UserDto add(UserForm userForm);
}
