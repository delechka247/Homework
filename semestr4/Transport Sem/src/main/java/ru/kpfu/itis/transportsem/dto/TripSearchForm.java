package ru.kpfu.itis.transportsem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.transportsem.models.Trip;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripSearchForm {
    private Integer priceFrom;
    private Integer priceTo;
    private Trip.City fromCity;
    private Trip.City toCity;
    private Date departureDateStart;
    private Date departureDateFinish;
    private Date arrivalDateStart;
    private Date arrivalDateFinish;
    private Trip.Transport transport;
}