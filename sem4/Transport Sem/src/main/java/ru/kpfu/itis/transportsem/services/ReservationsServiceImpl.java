package ru.kpfu.itis.transportsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportsem.dto.ReservationDto;
import ru.kpfu.itis.transportsem.models.Reservation;
import ru.kpfu.itis.transportsem.repositories.ReservationsRepository;
import ru.kpfu.itis.transportsem.repositories.TripsRepository;
import ru.kpfu.itis.transportsem.repositories.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationsServiceImpl implements ReservationsService {

    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TripsRepository tripsRepository;

    @Override
    public List<ReservationDto> getAllReservations() {
        return ReservationDto.from(reservationsRepository.findAll());
    }

    @Override
    public ReservationDto addReservation(ReservationDto reservationDto) {
        Reservation newReservation = Reservation.builder()
                .id(reservationDto.getId())
                .user(usersRepository.findById(reservationDto.getUserId()).orElseThrow(IllegalArgumentException::new))
                .trips(reservationDto.getTrips().stream()
                        .map(tripDto -> tripsRepository.findById(tripDto.getId()).orElseThrow(IllegalArgumentException::new))
                        .collect(Collectors.toList())
                )
                .build();
        reservationsRepository.save(newReservation);
        return ReservationDto.from(newReservation);
    }
}
