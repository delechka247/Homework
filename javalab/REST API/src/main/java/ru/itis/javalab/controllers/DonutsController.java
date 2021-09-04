package ru.itis.javalab.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.DonutDto;
import ru.itis.javalab.dto.DonutForm;
import ru.itis.javalab.models.Donut;
import ru.itis.javalab.services.DonutsService;

import java.util.List;

@RestController
public class DonutsController {

    @Autowired
    private DonutsService donutsService;

    @GetMapping("/donuts")
    public ResponseEntity<List<DonutDto>> getDonuts() {
        return ResponseEntity.ok(donutsService.getAllDonuts());
    }

    @ApiOperation(value = "Добавление пончика")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = DonutDto.class)})
    @PostMapping("/donuts")
    public ResponseEntity<DonutDto> addDonut(@RequestHeader("X-TOKEN") String token, @RequestBody DonutForm donutForm) {
        return ResponseEntity.ok(donutsService.addDonut(donutForm));
    }

    @PutMapping("/donuts/{donut-id}")
    public ResponseEntity<DonutDto> updateDonut(@RequestHeader("X-TOKEN") String token, @PathVariable("donut-id") Long donutId, @RequestBody DonutForm donutForm) {
        return ResponseEntity.ok(donutsService.updateDonut(donutId, donutForm));
    }

    @DeleteMapping("/donuts/{donut-id}")
    public ResponseEntity<?> deleteDonut(@RequestHeader("X-TOKEN") String token, @PathVariable("donut-id") Long donutId) {
        donutsService.deleteDonut(donutId);
        return ResponseEntity.ok().build();
    }

}
