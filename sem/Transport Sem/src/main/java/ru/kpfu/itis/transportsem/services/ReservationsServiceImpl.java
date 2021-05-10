package ru.kpfu.itis.transportsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportsem.dto.ReservationDto;
import ru.kpfu.itis.transportsem.models.Reservation;
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
        return null;
    }

    @Override
    public ReservationDto addReservation(List<Long> tripIdList, String token) {
        Reservation newReservation = Reservation.builder()
                .user(jwtDecoder.getUserFromJwt(token))
                .trips(tripIdList.stream()
                        .map(tripId -> tripsRepository.findById(tripId).orElseThrow(IllegalArgumentException::new))
                        .collect(Collectors.toList())
                )
                .build();
        reservationsRepository.save(newReservation);
        return ReservationDto.from(newReservation);
    }
}
