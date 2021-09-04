package ru.kpfu.itis.transportsem.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportsem.dto.ReservationDto;
import ru.kpfu.itis.transportsem.dto.TripDto;
import ru.kpfu.itis.transportsem.models.Reservation;
import ru.kpfu.itis.transportsem.services.ReservationsService;

import javax.annotation.security.PermitAll;
import java.util.Collections;
import java.util.List;

@RestController
public class ReservationsController {

    @Autowired
    private ReservationsService reservationsService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Получение всех броней")
    @GetMapping("/reservation")
    public List<ReservationDto> getReservations(@RequestHeader("TOKEN") String token) {
        return reservationsService.getAllReservations();
    }

    @ApiOperation(value = "Бронирование")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно забронировано", response = ReservationDto.class)})
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/reservation")
    public ReservationDto addTripReservation(@RequestHeader("TOKEN") String token, @RequestBody List<Long> tripIdList) {
        return reservationsService.addReservation(tripIdList, token);
    }
}
