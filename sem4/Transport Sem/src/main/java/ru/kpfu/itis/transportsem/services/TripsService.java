package ru.kpfu.itis.transportsem.services;

import ru.kpfu.itis.transportsem.dto.TripDto;
import ru.kpfu.itis.transportsem.dto.TripSearchForm;

import java.util.List;
import java.util.Optional;

public interface TripsService {
    List<TripDto> getAllTrips();
    List<TripDto> getTripsByParameters(TripSearchForm tripSearchForm);
    TripDto getOneById(Long id);
    TripDto addTrip(TripDto tripDto);
    TripDto updateTrip(Long tripId, TripDto tripDto);
    void tripReservation(Long tripId);
    void deleteTrip(Long tripId);
}
