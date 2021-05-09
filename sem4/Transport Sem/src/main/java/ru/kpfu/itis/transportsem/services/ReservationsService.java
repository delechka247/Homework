package ru.kpfu.itis.transportsem.services;

import ru.kpfu.itis.transportsem.dto.ReservationDto;
import ru.kpfu.itis.transportsem.models.Reservation;

import java.util.List;

public interface ReservationsService {
    List<ReservationDto> getAllReservations();
    ReservationDto addReservation(ReservationDto reservationDto);
}
