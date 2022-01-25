package ru.itis.javalab.statementsservice.redis.services;

import ru.itis.javalab.statementsservice.models.User;

public interface RedisUsersService {
    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);
}