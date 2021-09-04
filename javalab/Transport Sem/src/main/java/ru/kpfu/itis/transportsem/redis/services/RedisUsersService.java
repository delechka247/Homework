package ru.kpfu.itis.transportsem.redis.services;

import ru.kpfu.itis.transportsem.models.User;

public interface RedisUsersService {
    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);

    boolean checkToken(String token, User user);
}