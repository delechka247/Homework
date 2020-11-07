package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findOneByUUID(UUID uuid);
    List<User> findAllByAge(int age);

}

