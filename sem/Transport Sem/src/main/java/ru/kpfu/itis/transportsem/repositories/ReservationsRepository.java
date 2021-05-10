package ru.kpfu.itis.transportsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.transportsem.models.Reservation;

public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
}
