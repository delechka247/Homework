package ru.kpfu.itis.transportsem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.transportsem.models.Trip;
import ru.kpfu.itis.transportsem.models.User;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
    private Long id;
    private Integer price;
    private Trip.City fromCity;
    private Trip.City toCity;
    private Date departureDate;
    private Date arrivalDate;
    private Trip.Transport transport;
    private Integer number;

    public static TripDto from(Trip trip) {
        TripDto result = TripDto.builder()
                .id(trip.getId())
                .fromCity(trip.getFromCity())
                .toCity(trip.getToCity())
                .departureDate(trip.getDepartureDate())
                .arrivalDate(trip.getArrivalDate())
                .price(trip.getPrice())
                .number(trip.getNumber())
                .transport(trip.getTransport())
                .build();
        return result;
    }

    public static List<TripDto> from(List<Trip> trips) {
        return trips.stream().map(TripDto::from).collect(Collectors.toList());
    }
}
