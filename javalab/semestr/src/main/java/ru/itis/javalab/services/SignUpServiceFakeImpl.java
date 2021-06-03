package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.utils.EmailUtil;
import ru.itis.javalab.utils.MailsGenerator;

import java.util.UUID;

@Profile("dev")
@Service
public class SignUpServiceFakeImpl  implements SignUpService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void addUser(UserForm userForm) {
        User newUser = User.builder()
                .firstName("FAKE")
                .lastName("FAKEOFF")
                .email("fakeemail@email.ru")
                .stateForEmail(User.StateForEmail.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .build();


        //usersRepository.save(newUser);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());

        System.out.println("From: " + from);
        System.out.println("To: " + newUser.getEmail());
        System.out.println(confirmMail);
    }

}