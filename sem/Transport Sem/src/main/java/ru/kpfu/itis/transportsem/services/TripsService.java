package ru.kpfu.itis.transportsem.services;

import ru.kpfu.itis.transportsem.dto.TripDto;

import java.util.List;
import java.util.Optional;

public interface TripsService {
    List<TripDto> getAllTrips();
    Optional<List<TripDto>> getTripsByFromAndTo(String from, String to);
    TripDto getOneById(Long id);
    TripDto addTrip(TripDto tripDto);
    TripDto updateTrip(Long tripId, TripDto tripDto);
    void deleteTrip(Long tripId);
}
