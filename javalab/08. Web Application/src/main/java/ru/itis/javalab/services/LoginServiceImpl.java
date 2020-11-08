package ru.itis.javalab.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoginServiceImpl implements ru.itis.javalab.services.LoginService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public LoginServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String login(String email, String password) {
        if (email.equals("") || password.equals(""))
            return "Found empty field";

        List<User> oneUser = usersRepository.findOneByEmail(email);

        if (oneUser.size() > 0) {
            if(passwordEncoder.matches(password, oneUser.get(0).getHashPassword()))
                return "ok";
            else
                return "Wrong password";
        } else {
            return "This email is not registered";
        }

    }

    @Override
    public UUID getUUIDForSession(String email) {
        return usersRepository.findOneByEmail(email).get(0).getUuid();
    }
}
