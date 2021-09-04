package ru.kpfu.itis.transportsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportsem.dto.ReservationDto;
import ru.kpfu.itis.transportsem.models.Reservation;
import ru.kpfu.itis.transportsem.models.Trip;
import ru.kpfu.itis.transportsem.repositories.ReservationsRepository;
import ru.kpfu.itis.transportsem.repositories.TripsRepository;
import ru.kpfu.itis.transportsem.repositories.UsersRepository;
import ru.kpfu.itis.transportsem.security.jwt.utils.JwtDecoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationsServiceImpl implements ReservationsService {

    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private TripsRepository tripsRepository;


    @Override
    public List<ReservationDto> getAllReservations() {
        return ReservationDto.from(reservationsRepository.findAll());
    }

    @Override
    public List<ReservationDto> getUsersReservations(String token) {
        return ReservationDto.from(reservationsRepository.findAllByUser(jwtDecoder.getUserFromJwt(token)).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public ReservationDto addReservation(List<Long> tripIdList, String token) {
        Reservation newReservation = Reservation.builder()
                .user(jwtDecoder.getUserFromJwt(token))
                .trips(tripIdList.stream()
                        .filter(tripId -> tripsRepository.findById(tripId).orElseThrow(IllegalArgumentException::new).getNumber() >= tripIdList.stream().filter(x -> x.equals(tripId)).count())
                        .map(tripId -> tripsRepository.findById(tripId).orElseThrow(IllegalArgumentException::new))
                        .collect(Collectors.toList())
                )
                .build();
        if (newReservation.getTrips().size() <= 0)
            throw new IllegalArgumentException("Столько билетов нет в наличии");
        else {
            reservationsRepository.save(newReservation);
            newReservation.getTrips().stream().forEach(trip -> {
                Trip tripForUpdate = tripsRepository.findById(trip.getId())
                        .orElseThrow(IllegalArgumentException::new);
                tripForUpdate.setNumber(tripForUpdate.getNumber() - 1);
                tripsRepository.save(tripForUpdate);
            } );
        }


        return ReservationDto.from(newReservation);
    }
}
