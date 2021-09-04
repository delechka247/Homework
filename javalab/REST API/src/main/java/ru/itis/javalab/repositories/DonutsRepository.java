package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Donut;
import ru.itis.javalab.models.User;

import java.util.List;

public interface DonutsRepository extends JpaRepository<Donut, Long> {
    List<Donut> findAllByIsDeletedIsFalse();
}
