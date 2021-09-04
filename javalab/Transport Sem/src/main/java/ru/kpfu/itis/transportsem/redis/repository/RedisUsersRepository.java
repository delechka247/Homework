package ru.kpfu.itis.transportsem.redis.repository;

import ru.kpfu.itis.transportsem.redis.models.RedisUser;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}