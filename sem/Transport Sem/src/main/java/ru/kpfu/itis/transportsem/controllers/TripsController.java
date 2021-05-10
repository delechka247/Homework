package ru.kpfu.itis.transportsem.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportsem.dto.ReservationDto;
import ru.kpfu.itis.transportsem.dto.TripDto;
import ru.kpfu.itis.transportsem.models.Trip;
import ru.kpfu.itis.transportsem.services.ReservationsService;
import ru.kpfu.itis.transportsem.services.TripsService;

import javax.annotation.security.PermitAll;
import java.util.Collections;
import java.util.List;

@RestController
public class TripsController {

    @Autowired
    private TripsService tripsService;

    @Autowired
    private ReservationsService reservationsService;

    @PermitAll
    @ApiOperation(value = "Получение поездок")
    @GetMapping("/trips")
    public List<TripDto> getTrips() {
        return tripsService.getAllTrips();
    }

    @PermitAll
    @ApiOperation(value = "Получение одной поездки")
    @GetMapping("/trips/{trip-id}")
    public TripDto getTrip(@PathVariable("trip-id") Long tripId) {
        return tripsService.getOneById(tripId);
    }

    @ApiOperation(value = "Бронь одной поездки")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно забронировано", response = ReservationDto.class)})
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/trips/{trip-id}/reservation")
    public ReservationDto addTripReservation(@RequestHeader("TOKEN") String token, @PathVariable("trip-id") Long tripId) {
        return reservationsService.addReservation(Collections.singletonList(tripId), token);
    }

    @ApiOperation(value = "Добавление поездки")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = TripDto.class)})
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/trips")
    public TripDto addTrip(@RequestHeader("TOKEN") String token, @RequestBody TripDto tripDto) {
        return tripsService.addTrip(tripDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Изменение поездки")
    @PutMapping("/trips/{trip-id}")
    public TripDto updateTrip(@RequestHeader("TOKEN") String token, @PathVariable("trip-id") Long tripId, @RequestBody TripDto tripDto) {
        return tripsService.updateTrip(tripId, tripDto);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @DeleteMapping("/trips/{trip-id}")
//    public ResponseEntity<?> deleteTrip(@RequestHeader("TOKEN") String token, @PathVariable("trip-id") Long tripId) {
//        tripsService.deleteTrip(tripId);
//        return ResponseEntity.ok().build();
//    }

}
