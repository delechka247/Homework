package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.DonutDto;
import ru.itis.javalab.dto.DonutForm;
import ru.itis.javalab.dto.OrderDto;
import ru.itis.javalab.models.Donut;
import ru.itis.javalab.models.Order;
import ru.itis.javalab.services.OrdersService;

import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getOrders(@RequestHeader("X-TOKEN") String token) {
        return ResponseEntity.ok(ordersService.getAllOrders());
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> addDonut(@RequestHeader("X-TOKEN") String token, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(ordersService.addOrder(orderDto));
    }

}
