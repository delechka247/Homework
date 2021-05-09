package ru.kpfu.itis.transportsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.transportsem.models.Trip;
import ru.kpfu.itis.transportsem.models.User;

import java.util.Optional;

public interface TripsRepository extends JpaRepository<Trip, Long> {
    Optional<Trip> findByFromCityAndToCity(Trip.City fromCity, Trip.City toCity);
}
