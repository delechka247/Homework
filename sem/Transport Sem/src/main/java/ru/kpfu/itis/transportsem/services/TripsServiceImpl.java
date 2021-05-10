package ru.kpfu.itis.transportsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportsem.dto.TripDto;
import ru.kpfu.itis.transportsem.models.Trip;
import ru.kpfu.itis.transportsem.repositories.TripsRepository;

import java.util.List;
import java.util.Optional;

import static ru.kpfu.itis.transportsem.dto.TripDto.from;

@Service
public class TripsServiceImpl implements TripsService {

    @Autowired
    TripsRepository tripsRepository;

    @Override
    public List<TripDto> getAllTrips() {
        return from(tripsRepository.findAll());
    }

    @Override
    public TripDto getOneById(Long id) {
        TripDto tripDto = TripDto.builder().build();
        Optional<Trip> optionalTrip = tripsRepository.findById(id);
        if (optionalTrip.isPresent()) {
            tripDto = from(optionalTrip.get());
        }
        return tripDto;
    }

    @Override
    public TripDto addTrip(TripDto tripDto) {
        Trip trip = Trip.builder()
                .arrivalDate(tripDto.getArrivalDate())
                .departureDate(tripDto.getDepartureDate())
                .fromCity(tripDto.getFromCity())
                .toCity(tripDto.getToCity())

                .build();
        return tripDto;
    }

    @Override
    public TripDto updateTrip(Long tripId, TripDto tripDto) {
        Trip tripForUpdate = tripsRepository.findById(tripId)
                .orElseThrow(IllegalArgumentException::new);
        tripForUpdate.setNumber(tripDto.getNumber());
        tripForUpdate.setArrivalDate(tripDto.getArrivalDate());
        tripForUpdate.setDepartureDate(tripDto.getDepartureDate());
        tripForUpdate.setFromCity(tripDto.getFromCity());
        tripForUpdate.setToCity(tripDto.getToCity());
        tripForUpdate.setPrice(tripDto.getPrice());
        tripForUpdate.setTransport(tripDto.getTransport());
        tripsRepository.save(tripForUpdate);
        return from(tripForUpdate);
    }

    @Override
    public void deleteTrip(Long tripId) {

    }

    @Override
    public Optional<List<TripDto>> getTripsByFromAndTo(String from, String to) {
        return Optional.empty();
    }
}
