package ru.itis.javalab.statementsservice.redis.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.javalab.statementsservice.redis.models.RedisUser;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}