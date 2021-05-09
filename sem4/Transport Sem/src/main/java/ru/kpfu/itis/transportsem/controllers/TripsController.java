package ru.kpfu.itis.transportsem.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportsem.dto.TripDto;
import ru.kpfu.itis.transportsem.models.Trip;
import ru.kpfu.itis.transportsem.services.TripsService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
public class TripsController {

    @Autowired
    private TripsService tripsService;

    @PermitAll
    @GetMapping("/trips")
    public List<TripDto> getTrips() {
        return tripsService.getAllTrips();
    }

    @PermitAll
    @GetMapping("/trips/{trip-id}")
    public TripDto getTrip(@PathVariable("trip-id") Long tripId) {
        return tripsService.getOneById(tripId);
    }


    @ApiOperation(value = "Добавление поездки")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = TripDto.class)})
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/trips")
    public TripDto addTrip(@RequestHeader("TOKEN") String token, @RequestBody TripDto tripDto) {
        return tripsService.addTrip(tripDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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
