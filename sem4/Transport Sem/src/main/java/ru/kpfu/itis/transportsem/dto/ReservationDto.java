package ru.kpfu.itis.transportsem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.transportsem.models.Reservation;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private Long userId;
    private List<TripDto> trips;

    public static ReservationDto from(Reservation reservation) {
        ReservationDto result = ReservationDto.builder()
                .id(reservation.getId())
                .userId(reservation.getUser().getId())
                .trips(TripDto.from(reservation.getTrips()))
                .build();
        return result;
    }

    public static List<ReservationDto> from(List<Reservation> orders) {
        return orders.stream().map(ReservationDto::from).collect(Collectors.toList());
    }
}