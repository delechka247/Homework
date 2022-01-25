package ru.itis.javalab.statementsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.statementsservice.dto.UserDto;
import ru.itis.javalab.statementsservice.models.User;
import ru.itis.javalab.statementsservice.redis.services.RedisUsersService;
import ru.itis.javalab.statementsservice.repositories.UsersRepository;
import ru.itis.javalab.statementsservice.security.jwt.utils.JwtDecoder;

@Service
public class UsersServiceImpl implements ru.itis.javalab.statementsservice.services.UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private RedisUsersService redisUsersService;

    @Override
    public void blockUser(Long userId) {
        User user = usersRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        redisUsersService.addAllTokensToBlackList(user);
    }

    @Override
    public UserDto getUserFromJwt(String token) {
        User user = jwtDecoder.getUserFromJwt(token);

        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDto getUserById(Long id) {
        return UserDto.from(usersRepository.findUserById(id).get());
    }

}
