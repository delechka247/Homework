package ru.kpfu.itis.transportsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportsem.dto.UserDto;
import ru.kpfu.itis.transportsem.dto.UserForm;
import ru.kpfu.itis.transportsem.models.User;
import ru.kpfu.itis.transportsem.repositories.UsersRepository;

import static ru.kpfu.itis.transportsem.dto.UserDto.from;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto add(UserForm userForm) {
        User newUser = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .state(User.State.ACTIVE)
                .role(User.Role.USER)
                .build();
        usersRepository.save(newUser);
        return from(newUser);
    }
}