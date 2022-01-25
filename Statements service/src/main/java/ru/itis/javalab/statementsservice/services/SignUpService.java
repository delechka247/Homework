package ru.itis.javalab.statementsservice.services;

import ru.itis.javalab.statementsservice.dto.UserDto;
import ru.itis.javalab.statementsservice.dto.UserForm;

public interface SignUpService{
    UserDto add(UserForm userForm);
}
