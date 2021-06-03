package ru.itis.javalab.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.dto.UserForm;


public interface SignUpService {
    void addUser(UserForm userForm);
}
