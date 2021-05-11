package ru.kpfu.itis.transportsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.transportsem.models.Trip;
import ru.kpfu.itis.transportsem.models.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TripsRepository extends JpaRepository<Trip, Long> {

    Optional<List<Trip>> findAllByArrivalDateIsAfterAndArrivalDateIsBeforeAndDepartureDateAfterAndAndDepartureDateBeforeAndPriceAfterAndPriceBeforeAndTransportAndFromCityAndToCity(
            Date arrivalDateStart, Date arrivalDateStartFinish,
            Date departureDateStart, Date departureDateFinish,
            Integer priceFrom, Integer priceTo,
            Trip.Transport transport,
            Trip.City fromCity, Trip.City toCity
    );

    Optional<List<Trip>> findAllByArrivalDateIsAfterAndArrivalDateIsBeforeAndDepartureDateAfterAndAndDepartureDateBefore(
            Date arrivalDateStart, Date arrivalDateStartFinish,
            Date departureDateStart, Date departureDateFinish
    );

    Optional<List<Trip>> findAllByPriceAfterAndPriceBefore(
            Integer priceFrom, Integer priceTo
    );

    Optional<List<Trip>> findAllByFromCityAndToCity(
            Trip.City fromCity, Trip.City toCity
    );

    Optional<List<Trip>> findAllByTransport(
            Trip.Transport transport
    );


}
