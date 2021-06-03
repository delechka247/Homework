package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.utils.EmailUtil;
import ru.itis.javalab.utils.MailsGenerator;

import java.util.UUID;

@Profile("master")
@Service
public class SignUpServiceImpl implements SignUpService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void addUser(UserForm userForm) {
        User newUser = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .stateForEmail(User.StateForEmail.NOT_CONFIRMED)
                .state(User.State.ACTIVE)
                .role(User.Role.USER)
                .confirmCode(UUID.randomUUID().toString())
                .build();
        usersRepository.save(newUser);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());

        emailUtil.sendMail(newUser.getEmail(), "Регистрация", from, confirmMail);
    }

}
