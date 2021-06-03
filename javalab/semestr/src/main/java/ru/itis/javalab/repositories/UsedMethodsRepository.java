package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.javalab.models.UsedMethod;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Map;

public interface UsedMethodsRepository extends JpaRepository<UsedMethod, Long> {

    @Query(nativeQuery = true, value = "select name from used_method")
    List<String> countMethods();

}
