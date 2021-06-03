package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.javalab.models.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//public interface UsersRepository extends CrudRepository<User> {
//    Optional<User> findFirstByFirstnameAndLastname(String firstName, String lastName);
//    Optional<User> findFirstByEmail(String email);
//    void confirm(UUID confirmCode);
//}
public interface UsersRepository extends JpaRepository<User, Long> {

    //void confirm(UUID confirmCode);

    @Transactional
    @Modifying
    @Query("update User user set user.stateForEmail = :value where user.confirmCode = :code")
    void confirm(@Param("code") String code, @Param("value") User.StateForEmail value);

    Optional<User> findByEmail(String email);
}

