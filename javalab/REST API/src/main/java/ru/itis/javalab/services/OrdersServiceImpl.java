package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.DonutDto;
import ru.itis.javalab.dto.OrderDto;
import ru.itis.javalab.models.Order;
import ru.itis.javalab.repositories.DonutsRepository;
import ru.itis.javalab.repositories.OrdersRepository;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.itis.javalab.dto.OrderDto.from;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DonutsRepository donutsRepository;

    @Override
    public List<OrderDto> getAllOrders() {
        return from(ordersRepository.findAll());
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order newOrder = Order.builder()
                .id(orderDto.getId())
                .user(usersRepository.findById(orderDto.getUserId()).orElseThrow(IllegalArgumentException::new))
                .donuts(orderDto.getDonuts().stream()
                        .map(donutDto -> donutsRepository.findById(donutDto.getId()).orElseThrow(IllegalArgumentException::new))
                        .collect(Collectors.toList())
                )
                .build();
        ordersRepository.save(newOrder);
        return from(newOrder);
    }
}
