package ru.itis.javalab.services;

import ru.itis.javalab.dto.DonutDto;
import ru.itis.javalab.dto.DonutForm;
import ru.itis.javalab.dto.OrderDto;
import ru.itis.javalab.models.Donut;

import java.util.List;

public interface OrdersService {
    List<OrderDto> getAllOrders();
    OrderDto addOrder(OrderDto orderDto);
}
