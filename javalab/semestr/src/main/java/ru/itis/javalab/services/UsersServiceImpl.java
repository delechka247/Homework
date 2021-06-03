package ru.itis.javalab.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static ru.itis.javalab.dto.UserDto.from;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return from(usersRepository.findAll());
    }


    @Override
    public UserDto getUser(Long userId) {
        return from(usersRepository.findById(userId).orElse(null));
    }

    @Override
    public void confirmEmail(UUID confirmCode) {
        usersRepository.confirm(confirmCode.toString(), User.StateForEmail.CONFIRMED);
    }

    @Override
    public void banAll() {
        List<User> users = usersRepository.findAll();
        for (User user : users) {
            if (!user.isAdmin()) {
                user.setState(User.State.BANNED);
                usersRepository.save(user);
            }
        }
    }


}

