package ru.kpfu.itis.transportsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportsem.dto.TripDto;
import ru.kpfu.itis.transportsem.dto.TripSearchForm;
import ru.kpfu.itis.transportsem.models.Trip;
import ru.kpfu.itis.transportsem.repositories.TripsRepository;

import java.util.Collections;
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
    public List<TripDto> getTripsByParameters(TripSearchForm tripSearchForm) {
        boolean city = tripSearchForm.getFromCity() != null && tripSearchForm.getToCity() != null;
        boolean date = tripSearchForm.getArrivalDateStart() != null &&
                tripSearchForm.getArrivalDateFinish() != null &&
                tripSearchForm.getDepartureDateStart() != null &&
                tripSearchForm.getDepartureDateFinish() != null;
        boolean price = tripSearchForm.getPriceFrom() != null && tripSearchForm.getPriceTo() != null;
        boolean transport = tripSearchForm.getTransport() != null;

        if (city && date && price && transport) {
            return TripDto.from(tripsRepository.findAllByArrivalDateIsAfterAndArrivalDateIsBeforeAndDepartureDateAfterAndAndDepartureDateBeforeAndPriceAfterAndPriceBeforeAndTransportAndFromCityAndToCity(
                    tripSearchForm.getArrivalDateStart(),
                    tripSearchForm.getArrivalDateFinish(),
                    tripSearchForm.getDepartureDateStart(),
                    tripSearchForm.getDepartureDateFinish(),
                    tripSearchForm.getPriceFrom(),
                    tripSearchForm.getPriceTo(),
                    tripSearchForm.getTransport(),
                    tripSearchForm.getFromCity(),
                    tripSearchForm.getToCity()
            ).orElseThrow(IllegalArgumentException::new));
        }

        if (city) {
            return TripDto.from(tripsRepository.findAllByFromCityAndToCity(
                    tripSearchForm.getFromCity(),
                    tripSearchForm.getToCity()
            ).orElseThrow(IllegalArgumentException::new));
        }

        if (date) {
            return TripDto.from(tripsRepository.findAllByArrivalDateIsAfterAndArrivalDateIsBeforeAndDepartureDateAfterAndAndDepartureDateBefore(
                    tripSearchForm.getArrivalDateStart(),
                    tripSearchForm.getArrivalDateFinish(),
                    tripSearchForm.getDepartureDateStart(),
                    tripSearchForm.getDepartureDateFinish()
            ).orElseThrow(IllegalArgumentException::new));
        }

        if (price) {
            return TripDto.from(tripsRepository.findAllByPriceAfterAndPriceBefore(
                    tripSearchForm.getPriceFrom(),
                    tripSearchForm.getPriceTo()
            ).orElseThrow(IllegalArgumentException::new));
        }

        if (transport) {
            return TripDto.from(tripsRepository.findAllByTransport(
                    tripSearchForm.getTransport()
            ).orElseThrow(IllegalArgumentException::new));
        }
        return Collections.emptyList();
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
                .number(tripDto.getNumber())
                .price(tripDto.getPrice())
                .transport(tripDto.getTransport())
                .build();
        tripsRepository.save(trip);
        return TripDto.from(trip);
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
    public void tripReservation(Long tripId) {
        Trip tripForUpdate = tripsRepository.findById(tripId)
                .orElseThrow(IllegalArgumentException::new);
        tripForUpdate.setNumber(tripForUpdate.getNumber() - 1);
        tripsRepository.save(tripForUpdate);
    }
    




    @Override
    public void deleteTrip(Long tripId) {

    }
}
