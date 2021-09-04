package ru.kpfu.itis.transportsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.transportsem.models.Reservation;
import ru.kpfu.itis.transportsem.models.User;

import java.util.List;
import java.util.Optional;

public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
    Optional<List<Reservation>> findAllByUser(User user);
}
